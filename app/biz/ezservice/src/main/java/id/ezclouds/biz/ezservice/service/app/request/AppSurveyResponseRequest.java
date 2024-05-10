/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyResponseRequest.java, v 0.1 2024‐02‐18 1:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppSurveyResponseRequest {

    private String requestId;
    private String orgId;
    private String surveyId;
    private String submitterMemberId;
    private String questionVersion;
    private String responderDataEncoded;
    private String responseDataEncoded;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSubmitterMemberId() {
        return submitterMemberId;
    }

    public void setSubmitterMemberId(String submitterMemberId) {
        this.submitterMemberId = submitterMemberId;
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