/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.process;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SurveyResponseParseProcessRequest.java, v 0.1 2024‐08‐19 7:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SurveyResponseParseProcessRequest {

    private String orgId;
    private String surveyId;

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
}