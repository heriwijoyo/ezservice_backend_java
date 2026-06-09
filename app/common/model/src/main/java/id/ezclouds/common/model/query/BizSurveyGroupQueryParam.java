/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.query;

import id.ezclouds.common.model.constant.SurveyGroupQuery;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyGroupQueryParam.java, v 0.1 2024‐11‐23 9:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyGroupQueryParam {

    private String orgId;
    private String surveyId;
    private SurveyGroupQuery groupQuery;

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

    public SurveyGroupQuery getGroupQuery() {
        return groupQuery;
    }

    public void setGroupQuery(SurveyGroupQuery groupQuery) {
        this.groupQuery = groupQuery;
    }
}