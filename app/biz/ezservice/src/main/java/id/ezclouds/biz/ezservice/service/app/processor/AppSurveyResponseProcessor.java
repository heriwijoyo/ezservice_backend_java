/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.service.app.processor.parser.ParserType;
import id.ezclouds.biz.ezservice.service.app.processor.parser.SurveyDataParser;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyDataRJL001;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyDataRJL001Repository;
import id.ezclouds.biz.ezservice.service.app.processor.request.AppSurveyResponseProcessRequest;
import id.ezclouds.biz.ezservice.service.app.processor.result.ProcessResult;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyResponseProcessor.java, v 0.1 2024‐05‐10 10:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppSurveyResponseProcessor {

    @Autowired
    private AppSurveyDataRJL001Repository appSurveyDataRJL001Repository;

    public ProcessResult process(AppSurveyResponseProcessRequest request) {
        final ProcessResult result = new ProcessResult();

        System.out.println("--- Start Process SURVEY_RESPONSE ---");
        System.out.println(request);
        if (request == null || ParserType.getByCode(request.getParserCode()) == ParserType.UNKNOWN || StringUtil.isBlank(request.getParserMap())) {
            System.out.println("AppSurveyResponseProcessRequest is invalid");
            return result;
        }

        Map<String, String> parserMapping = null;
        try {
            parserMapping = new ObjectMapper()
                    .readValue(request.getParserMap(), new TypeReference<Map<String, String>>(){});
        } catch (Exception e) {
            System.out.println("error parsing configMap");
        }

        if (parserMapping == null || parserMapping.isEmpty()) {
            return result;
        }

        switch (ParserType.getByCode(request.getParserCode())) {
            case RJL_SURVEY_001:
                SurveyDataParser<AppSurveyDataRJL001> surveyDataParser = new SurveyDataParser<>(ParserType.RJL_SURVEY_001, new AppSurveyDataRJL001());
                setCommonInfo(surveyDataParser, request, parserMapping);
                AppSurveyDataRJL001 modelDO = surveyDataParser.parseToModel();
                appSurveyDataRJL001Repository.saveAndFlush(modelDO);
                break;
        }

        return result;
    }

    private void setCommonInfo(SurveyDataParser<?> surveyDataParser, AppSurveyResponseProcessRequest request, Map<String, String> parserMapping) {
        surveyDataParser.setResponseId(request.getResponseId());
        surveyDataParser.setOrgId(request.getOrgId());
        surveyDataParser.setSubmitterId(request.getSubmitterId());
        surveyDataParser.setQuestionVersion(request.getQuestionVersion());
        surveyDataParser.setResponderData(request.getResponderData());
        surveyDataParser.setParserMapping(parserMapping);
        surveyDataParser.setResponseData(request.getResponseData());
    }
}