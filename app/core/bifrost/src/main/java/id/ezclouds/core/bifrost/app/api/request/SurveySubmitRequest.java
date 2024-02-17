/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SurveySubmitRequest.java, v 0.1 2024‐02‐18 1:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SurveySubmitRequest extends ApiRequest {

    private String surveyId;
    private String questionVersion;
    private String responderDataEncoded;
    private String responseDataEncoded;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public String getResponderDataEncoded() {
        return responderDataEncoded;
    }

    public void setResponderDataEncoded(String responderDataEncoded) {
        this.responderDataEncoded = responderDataEncoded;
    }

    public String getResponseDataEncoded() {
        return responseDataEncoded;
    }

    public void setResponseDataEncoded(String responseDataEncoded) {
        this.responseDataEncoded = responseDataEncoded;
    }
}