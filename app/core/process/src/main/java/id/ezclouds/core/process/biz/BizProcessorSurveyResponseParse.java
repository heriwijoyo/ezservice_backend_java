/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.model.biz.survey.BizSurveyResponse;
import id.ezclouds.common.model.request.process.SurveyResponseParseProcessRequest;
import id.ezclouds.core.process.biz.inner.BizInnerProcessorSurveyResponseParse;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List<BizSurveyResponse> responses = bizInnerProcessorSurveyResponseParse
                .getSurveyResponses(processRequest.getOrgId(), processRequest.getSurveyId());

        System.out.println("response size: "+ responses.size());

        return true;
    }

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.SURVEY_RESPONSE_PARSE;
    }
}