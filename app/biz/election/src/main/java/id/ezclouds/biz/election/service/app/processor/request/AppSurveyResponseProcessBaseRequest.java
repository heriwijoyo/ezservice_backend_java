/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.processor.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyResponseProcessBaseRequest.java, v 0.1 2024‐05‐12 11:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppSurveyResponseProcessBaseRequest {

    private String orgId;
    private String surveyId;
    private String responseId;
    private String submitterId;
    private String questionVersion;
    private String parserCode;
    private String parserMap;

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

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public String getParserCode() {
        return parserCode;
    }

    public void setParserCode(String parserCode) {
        this.parserCode = parserCode;
    }

    public String getParserMap() {
        return parserMap;
    }

    public void setParserMap(String parserMap) {
        this.parserMap = parserMap;
    }
}