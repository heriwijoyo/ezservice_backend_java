/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.biz.election.service.app.processor.parser.ParserType;
import id.ezclouds.biz.election.service.app.processor.parser.SurveyDataParser;
import id.ezclouds.biz.election.service.app.processor.repo.AppSurveyDataRJL001;
import id.ezclouds.biz.election.service.app.processor.repo.AppSurveyDataRJL001Repository;
import id.ezclouds.biz.election.service.app.processor.request.AppSurveyResponseProcessBaseRequest;
import id.ezclouds.biz.election.service.app.processor.result.ProcessResult;
import id.ezclouds.biz.election.service.app.processor.repo.AppSurveyBaseData;
import id.ezclouds.biz.election.service.app.processor.request.AppSurveyResponseProcessRequest;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            result.setMessage("Invalid processor request");
            return result;
        }

        Map<String, String> parserMapping = null;
        try {
            parserMapping = new ObjectMapper()
                    .readValue(request.getParserMap(), new TypeReference<Map<String, String>>(){});
        } catch (Exception e) {}

        if (parserMapping == null || parserMapping.isEmpty()) {
            result.setMessage("Invalid parserMapping config");
            return result;
        }

        try {
            switch (ParserType.getByCode(request.getParserCode())) {
                case RJL_SURVEY_001:
                    SurveyDataParser<AppSurveyDataRJL001> surveyDataParser = new SurveyDataParser<>(new AppSurveyDataRJL001(), ParserType.RJL_SURVEY_001);
                    surveyDataParser.setCommonInfo(baseData -> setBaseCommonInfo(baseData, request));
                    AppSurveyDataRJL001 modelDO = surveyDataParser.parseToModel(
                            parserMapping,
                            request.getResponderData(),
                            request.getResponseData()
                    );
                    saveSurveyDataRJL001(modelDO);

                    result.setProcessId(modelDO.getId());
                    result.setProcessTime(DateUtil.getCurrentFormattedDate());
                    result.setSuccess(true);
                    result.setMessage("SUCCESS");
                    break;
            }
        } catch (Exception e) {
            result.setMessage("Parsing processor failed : " + e.getMessage());
        }

        return result;
    }

    @Transactional
    public void saveSurveyDataRJL001(AppSurveyDataRJL001 surveyDataRJL001) {
        appSurveyDataRJL001Repository.saveAndFlush(surveyDataRJL001);
    }

    private void setBaseCommonInfo(AppSurveyBaseData appSurveyBaseData, AppSurveyResponseProcessBaseRequest request) {
        appSurveyBaseData.setId(HashUtil.createHash(request.getOrgId(), request.getResponseId(), DateUtil.getCurrentFormattedDate()));
        appSurveyBaseData.setOrgId(request.getOrgId());
        appSurveyBaseData.setSurveyId(request.getSurveyId());
        appSurveyBaseData.setResponseId(request.getResponseId());
        appSurveyBaseData.setSubmitterId(request.getSubmitterId());
        appSurveyBaseData.setQuestionVersion(request.getQuestionVersion());
    }
}