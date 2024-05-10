/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor.parser;

import id.ezclouds.biz.ezservice.model.survey.QuestionnaireData;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyDataRJL001;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SurveyDataParser.java, v 0.1 2024‐05‐10 3:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SurveyDataParser<T extends Object> {

    private final ParserType parserType;
    private final T objectModel;
    private String responseId;
    private String orgId;
    private String submitterId;
    private String questionVersion;
    private Map<String, Object> responderData;
    private Map<String, String> parserMapping;
    private List<QuestionnaireData> responseData;

    public SurveyDataParser(ParserType parserType, T objectModel) {
        this.parserType = parserType;
        this.objectModel = objectModel;
    }

    public T parseToModel() {
        fillCommonInfo();
        fillResponseData();
        return objectModel;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public void setResponderData(Map<String, Object> responderData) {
        this.responderData = responderData;
    }

    public void setParserMapping(Map<String, String> parserMapping) {
        this.parserMapping = parserMapping;
    }

    public void setResponseData(List<QuestionnaireData> responseData) {
        this.responseData = responseData;
    }

    private void fillCommonInfo() {
        switch (parserType) {
            case RJL_SURVEY_001:
                fillRJLSurvey001();
                break;
        }
    }

    private void fillResponseData() {
        switch (parserType) {
            case RJL_SURVEY_001:
                ParserModelInjector.injectValue(objectModel, responderData, parserMapping);
                break;
        }
    }

    private void fillRJLSurvey001() {
        if (objectModel instanceof AppSurveyDataRJL001) {
            String currentTime = DateUtil.getCurrentFormattedDate();
            ((AppSurveyDataRJL001) objectModel).setResponseId(responseId);
            ((AppSurveyDataRJL001) objectModel).setId(HashUtil.createHash(orgId, submitterId, currentTime));
            ((AppSurveyDataRJL001) objectModel).setOrgId(orgId);
            ((AppSurveyDataRJL001) objectModel).setSubmitterId(submitterId);
            ((AppSurveyDataRJL001) objectModel).setQuestionVersion(questionVersion);
        }
    }
}