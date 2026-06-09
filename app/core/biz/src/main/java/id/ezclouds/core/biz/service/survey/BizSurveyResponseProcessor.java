/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.survey;

import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyResponseDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyResponseParserConfigDAO;
import id.ezclouds.common.facade.dal.member.CoreMemberDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponseProcessor.java, v 0.1 2024‐11‐18 3:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizSurveyResponseProcessor {

    private static List<EzCoreTopic> allowedTopics = Arrays.asList(
            EzCoreTopic.BIZ_SURVEY_RESPONSE_CREATE
    );

    @Autowired
    private BizSurveyResponseDAO bizSurveyResponseDAO;

    @Autowired
    private CoreMemberDAO coreMemberDAO;

    @Autowired
    private AppCommonDataSurveyDAO appCommonDataSurveyDAO;

    @Autowired
    private BizSurveyResponseParserConfigDAO bizSurveyResponseParserConfigDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private TransactionTemplate storeDataSurveyTemplate;

    @Autowired
    private TransactionTemplate updateSurveyResponseTemplate;

    @Async
    @EventListener
    public void handleEventForSurveyResponse(EzCommonEvent ezCommonEvent) {
        boolean isFilterIn = allowedTopics.contains(ezCommonEvent.getCoreTopic());
        if (!isFilterIn) {
            return;
        }

        String orgId = ezCommonEvent.getOrgId();
        String surveyResponseId = (String) ezCommonEvent.getPayload();

        BizSurveyResponse response = bizSurveyResponseDAO.getById(surveyResponseId);

        if (response != null && StringUtil.equals(orgId, response.getOrgId())) {
            String parserId = HashUtil.createHash(response.getOrgId(), response.getSurveyId(), response.getQuestionVersion());
            BizSurveyResponseParserConfig parserConfig = bizSurveyResponseParserConfigDAO.getById(parserId);

            AppCommonDataSurvey dataSurvey = null;
            String processMessage = "SUCCESS";
            try {
                dataSurvey = parseSurveyResponse(response, parserConfig);
                CoreMember coreMember = coreMemberDAO.getById(dataSurvey.submitterId);
                dataSurvey.submitterName = coreMember.getName();
            } catch (Exception e) {
                processMessage = e.getMessage();
            }

            if (dataSurvey != null) {
                final AppCommonDataSurvey finalDataSurvey = dataSurvey;
                storeDataSurveyTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        appCommonDataSurveyDAO.store(finalDataSurvey);
                    }
                });
            }

            final String responseId = response.getId();
            final String processId = dataSurvey == null ? null : dataSurvey.dataId;
            final String processTime = DateUtil.getCurrentFormattedDateMillis();
            final String message = processMessage;

            updateSurveyResponseTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    bizSurveyResponseDAO.updateResponse(
                            responseId,
                            processId,
                            processTime,
                            message
                    );
                }
            });
        }

    }

    private AppCommonDataSurvey parseSurveyResponse(BizSurveyResponse response, BizSurveyResponseParserConfig parserConfig) throws Exception {
        String currentTime = DateUtil.getCurrentFormattedDate();
        response.setProcessTime(currentTime);

        AppCommonDataSurvey commonDataSurvey = new AppCommonDataSurvey();
        commonDataSurvey.dataId = HashUtil.createHash(response.getId(), currentTime);
        commonDataSurvey.orgId = response.getOrgId();
        commonDataSurvey.surveyId = response.getSurveyId();
        commonDataSurvey.responseId = response.getId();
        commonDataSurvey.questionVersion = response.getQuestionVersion();
        commonDataSurvey.submitterId = response.getSubmitterMemberId();
        commonDataSurvey.createdTime = response.getCreatedTime();

        Map<String, String> parserMap = bizObjectMapperService
                .jsonToMap(parserConfig.getParserMapping());

        Map<String, String> responderMap = bizObjectMapperService
                .jsonToMap(response.getResponderData());

        Map<String, String> responseMap = bizObjectMapperService
                .parseSurveyResponse(response.getResponseData());

        responderMap.putAll(responseMap);
        parseByReflection(commonDataSurvey, parserMap, responderMap);

        return commonDataSurvey;
    }

    private void parseByReflection(AppCommonDataSurvey commonDataSurvey, Map<String, String> parserMap, Map<String, String> valueMap) throws Exception {
        for (Field field : commonDataSurvey.getClass().getDeclaredFields()) {
            String fieldName = field.getName();

            String valueKey = parserMap.get(fieldName);
            if (StringUtil.isNotBlank(valueKey)) {
                String fieldValue = valueMap.get(valueKey);

                if (fieldValue != null) {
                    field.setAccessible(true);
                    field.set(commonDataSurvey, fieldValue.trim());
                }
            }
        }
    }
}