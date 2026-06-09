/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

import id.ezclouds.common.model.request.api.ApiRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SurveyFormRequest.java, v 0.1 2024‐02‐17 6:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SurveyFormRequest extends ApiRequest {

    private String surveyId;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }
}