/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor.parser;

import id.ezclouds.biz.ezservice.model.survey.QuestionnaireData;
import id.ezclouds.biz.ezservice.service.app.processor.repo.AppSurveyDataRJL001;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
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
    private Map<String, String> mappingConfig;
    private Map<String, Object> responderData;
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

    public void setMappingConfig(Map<String, String> mappingConfig) {
        this.mappingConfig = mappingConfig;
    }

    public void setResponderData(Map<String, Object> responderData) {
        this.responderData = responderData;
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
                Map<String, Object> mapValues = composeMapValueRJL001(mappingConfig, responderData, responseData);
                ParserModelInjector.injectValue(objectModel, mapValues);
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

    private Map<String, Object> composeMapValueRJL001(Map<String, String> mappingConfig, Map<String, Object> responderData, List<QuestionnaireData> responseData) {
        Map<String, Object> composedMapValue = new HashMap<>();

        Map<String, QuestionnaireData> responseDataMap = new HashMap<>();
        for (QuestionnaireData questionnaireData : responseData) {
            responseDataMap.put(questionnaireData.getQuestionId(), questionnaireData);
        }

        for (Map.Entry<String, String> entry : mappingConfig.entrySet()) {
            String entryKey = entry.getKey();
            String entryValue = entry.getValue();

            if (entryKey.startsWith("q_")) {
                if (entryKey.endsWith("_answer")) {
                    composedMapValue.put(entryKey, getQuestionAnswer(responseDataMap.get(entryValue)));
                }
                if (entryKey.endsWith("_other")) {
                    composedMapValue.put(entryKey, getQuestionOther(responseDataMap.get(entryValue)));
                }
            } else {
                composedMapValue.put(entryKey, getResponderValue(entryValue, responderData));
            }
        }
        return composedMapValue;
    }

    private String getQuestionAnswer(QuestionnaireData data) {
        if (data != null && data.getSelectedOptions() != null && !data.getSelectedOptions().isEmpty()) {
            String[] answerKeys = new String[data.getSelectedOptions().size()];
            for (int i = 0; i < data.getSelectedOptions().size(); i++) {
                answerKeys[0] = data.getSelectedOptions().get(i).getKey();
            }
            return String.join(",", answerKeys);
        }
        return null;
    }

    private String getQuestionOther(QuestionnaireData data) {
        if (data != null) {
            return data.getOther();
        }
        return null;
    }

    private Object getResponderValue(String key, Map<String, Object> responderData) {
        if (responderData == null) {
            return null;
        }
        if (key.contains(".")) {
            String[] keys = key.split("\\.");
            Object objectValue = responderData.get(keys[0]);
            if ((objectValue instanceof Map) && keys.length > 1) {
                Map<String, Object> objectMap = (Map<String, Object>) objectValue;
                Object subObjectValue = objectMap.get(keys[1]);
                if (subObjectValue instanceof Double) {
                    return String.format("%.25f", subObjectValue);
                }
                return subObjectValue;
            }
            return null;
        }

        Object oriValue = responderData.get(key);
        if (oriValue instanceof String) {
            if ("Laki - Laki".equals(oriValue)) {
                return "MALE";
            }
            if ("Perempuan".equals(oriValue)) {
                return "FEMALE";
            }
            return oriValue;
        }

        return String.valueOf(oriValue);
    }
}