/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ezclouds.core.bifrost.app.api.model.survey.QuestionnaireData;

import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SurveySubmitRequest.java, v 0.1 2024‐02‐18 1:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SurveySubmitRequest extends ApiRequest {

    private String surveyId;
    private String questionnaireVersion;
    private Map<String, Object> responderData;
    private List<QuestionnaireData> responseData;
    private String responderDataEncoded;
    private String responseDataEncoded;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestionnaireVersion() {
        return questionnaireVersion;
    }

    public void setQuestionnaireVersion(String questionnaireVersion) {
        this.questionnaireVersion = questionnaireVersion;
    }

    public Map<String, Object> getResponderData() {
        return responderData;
    }

    public void setResponderData(Map<String, Object> responderData) {
        this.responderData = responderData;
    }

    public List<QuestionnaireData> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<QuestionnaireData> responseData) {
        this.responseData = responseData;
    }

    public String getResponderDataEncoded() {
        try {
            return new ObjectMapper().writeValueAsString(getResponderData());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String getResponseDataEncoded() {
        try {
            return new ObjectMapper().writeValueAsString(getResponseData());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public void setResponseDataEncoded(String responseDataEncoded) {
        this.responseDataEncoded = responseDataEncoded;
    }
}