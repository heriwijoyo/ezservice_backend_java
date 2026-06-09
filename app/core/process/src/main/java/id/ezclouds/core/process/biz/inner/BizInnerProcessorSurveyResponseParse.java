/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.biz.AppCommonDataSurveyDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyResponseDAO;
import id.ezclouds.common.facade.dal.biz.BizSurveyResponseParserConfigDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessorSurveyResponseParse.java, v 0.1 2024‐08‐19 6:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessorSurveyResponseParse {

    @Autowired
    private BizSurveyResponseDAO bizSurveyResponseDAO;

    @Autowired
    private BizSurveyResponseParserConfigDAO bizSurveyResponseParserConfigDAO;

    @Autowired
    private AppCommonDataSurveyDAO appCommonDataSurveyDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    public List<BizSurveyResponse> getSurveyResponses(String orgId, String surveyId) {
        return bizSurveyResponseDAO.getResponse(orgId, surveyId);
    }

    public Map<String, BizSurveyResponseParserConfig> getParserConfigMap(String orgId, String surveyId) {
        Map<String, BizSurveyResponseParserConfig> parserConfigMap = new HashMap<>();
        List<BizSurveyResponseParserConfig> parserConfigs = bizSurveyResponseParserConfigDAO
                .getParserConfig(orgId, surveyId);

        for (BizSurveyResponseParserConfig parserConfig : parserConfigs) {
            parserConfigMap.put(parserConfig.getId(), parserConfig);
        }

        return parserConfigMap;
    }

    @Transactional
    public void storeCommonData(AppCommonDataSurvey dataSurvey) {
        appCommonDataSurveyDAO.store(dataSurvey);
    }

    @Transactional
    public void updateResponse(String responseId, String processId, String processTime, String processMessage) {
        bizSurveyResponseDAO.updateResponse(responseId, processId, processTime, processMessage);
    }

    public AppCommonDataSurvey parseResponse(BizSurveyResponse response, BizSurveyResponseParserConfig parserConfig) throws Exception {
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