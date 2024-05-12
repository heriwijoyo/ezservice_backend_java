/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.ezservice.service.app.processor.parser.ParserType;
import id.ezclouds.biz.ezservice.service.app.processor.parser.SurveyDataParser;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyBaseData;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyDataRJL001;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyDataRJL001Repository;
import id.ezclouds.biz.ezservice.service.app.processor.request.AppSurveyResponseProcessBaseRequest;
import id.ezclouds.biz.ezservice.service.app.processor.request.AppSurveyResponseProcessRequest;
import id.ezclouds.biz.ezservice.service.app.processor.result.ProcessResult;
import id.ezclouds.common.util.HashUtil;
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

        if (request == null || ParserType.getByCode(request.getParserCode()) == ParserType.UNKNOWN || StringUtil.isBlank(request.getParserMap())) {
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
                SurveyDataParser<AppSurveyDataRJL001> surveyDataParser = new SurveyDataParser<>(new AppSurveyDataRJL001(), ParserType.RJL_SURVEY_001);
                surveyDataParser.setCommonInfo(baseData -> setBaseCommonInfo(baseData, request));
                AppSurveyDataRJL001 modelDO = surveyDataParser.parseToModel(
                        parserMapping,
                        request.getResponderData(),
                        request.getResponseData()
                );
                appSurveyDataRJL001Repository.saveAndFlush(modelDO);
                break;
        }

        return result;
    }

    private void setBaseCommonInfo(AppSurveyBaseData appSurveyBaseData, AppSurveyResponseProcessBaseRequest request) {
        appSurveyBaseData.setId(HashUtil.createHash(request.getOrgId(), request.getResponseId()));
        appSurveyBaseData.setOrgId(request.getOrgId());
        appSurveyBaseData.setSurveyId(request.getSurveyId());
        appSurveyBaseData.setResponseId(request.getResponseId());
        appSurveyBaseData.setSubmitterId(request.getSubmitterId());
        appSurveyBaseData.setQuestionVersion(request.getQuestionVersion());
    }
}