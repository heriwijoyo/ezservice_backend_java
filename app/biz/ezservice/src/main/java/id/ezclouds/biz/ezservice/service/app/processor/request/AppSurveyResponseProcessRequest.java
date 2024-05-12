/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor.request;

import id.ezclouds.biz.ezservice.model.survey.QuestionnaireData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyResponseProcessRequest.java, v 0.1 2024‐05‐10 10:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppSurveyResponseProcessRequest extends AppSurveyResponseProcessBaseRequest {

    private Map<String, Object> responderData;
    private List<QuestionnaireData> responseData;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}