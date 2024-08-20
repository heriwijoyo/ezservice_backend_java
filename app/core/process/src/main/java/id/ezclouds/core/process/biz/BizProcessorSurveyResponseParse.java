/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.model.biz.survey.AppCommonDataSurvey;
import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.common.model.biz.survey.BizSurveyResponseParserConfig;
import id.ezclouds.common.model.request.process.SurveyResponseParseProcessRequest;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.process.biz.inner.BizInnerProcessorSurveyResponseParse;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessorSurveyResponseParse.java, v 0.1 2024‐08‐18 11:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessorSurveyResponseParse extends BizAsyncProcessor {

    @Autowired
    private BizInnerProcessorSurveyResponseParse bizInnerProcessorSurveyResponseParse;

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        SurveyResponseParseProcessRequest processRequest = (SurveyResponseParseProcessRequest) request;
        logData.add("ORG_ID="+ processRequest.getOrgId());
        logData.add("SURVEY_ID="+ processRequest.getSurveyId());

        List<BizSurveyResponse> responses = bizInnerProcessorSurveyResponseParse
                .getSurveyResponses(processRequest.getOrgId(), processRequest.getSurveyId())
                .stream()
                .filter(item -> StringUtil.isBlank(item.getProcessId()))
                .collect(Collectors.toList());

        logData.add("COUNT="+ responses.size());
        if (responses.size() < 1) {
            return true;
        }

        Map<String, BizSurveyResponseParserConfig> parserConfigMap = bizInnerProcessorSurveyResponseParse
                .getParserConfigMap(processRequest.getOrgId(), processRequest.getSurveyId());

        for (BizSurveyResponse response : responses) {
            String parserId = HashUtil.createHash(response.getOrgId(), response.getSurveyId(), response.getQuestionVersion());
            BizSurveyResponseParserConfig parserConfig = parserConfigMap.get(parserId);

            AppCommonDataSurvey dataSurvey = null;
            String processMessage = "SUCCESS";
            try {
                dataSurvey = bizInnerProcessorSurveyResponseParse
                        .parseResponse(response, parserConfig);
            } catch (Exception e) {
                processMessage = e.getMessage();
            }

            if (dataSurvey != null) {
                try {
                    bizInnerProcessorSurveyResponseParse
                            .storeCommonData(dataSurvey);
                } catch (Exception e2) {
                    processMessage = e2.getMessage();
                }
            }

            try {
                String processId = dataSurvey == null ? null : dataSurvey.dataId;
                bizInnerProcessorSurveyResponseParse
                        .updateResponse(response.getId(), processId, response.getProcessTime(), processMessage);
            } catch (Exception ignored) {}
        }

        return true;
    }

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.SURVEY_RESPONSE_PARSE;
    }
}