/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.survey;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponse.java, v 0.1 2024‐08‐19 6:16 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyResponse {

    private String id;
    private String requestId;
    private String orgId;
    private String surveyId;
    private String questionVersion;
    private String submitterMemberId;
    private String responderData;
    private String responseData;
    private String createdTime;
    private String processId;
    private String processTime;
    private String processMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public String getSubmitterMemberId() {
        return submitterMemberId;
    }

    public void setSubmitterMemberId(String submitterMemberId) {
        this.submitterMemberId = submitterMemberId;
    }

    public String getResponderData() {
        return responderData;
    }

    public void setResponderData(String responderData) {
        this.responderData = responderData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getProcessMessage() {
        return processMessage;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }
}