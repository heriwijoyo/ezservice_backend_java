/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.survey;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyForm.java, v 0.1 2024‐02‐15 5:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyForm {

    private String surveyId;
    private int questionnaireVersion;
    private List<ResponderForm> responderForms;
    private List<QuestionForm> questionnaireForms;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public int getQuestionnaireVersion() {
        return questionnaireVersion;
    }

    public void setQuestionnaireVersion(int questionnaireVersion) {
        this.questionnaireVersion = questionnaireVersion;
    }

    public List<ResponderForm> getResponderForms() {
        return responderForms;
    }

    public void setResponderForms(List<ResponderForm> responderForms) {
        this.responderForms = responderForms;
    }

    public List<QuestionForm> getQuestionnaireForms() {
        return questionnaireForms;
    }

    public void setQuestionnaireForms(List<QuestionForm> questionnaireForms) {
        this.questionnaireForms = questionnaireForms;
    }
}