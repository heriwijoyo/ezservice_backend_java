/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.survey;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: QuestionForm.java, v 0.1 2024‐02‐15 5:36 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class QuestionForm {

    private String qid;
    private boolean required;
    private String title;
    private int maxSelectionAllowed;
    private boolean hasOther;
    private String otherRequiredCondition;
    private String otherHint;
    private String questionnaireNumber;
    private String questionnaireNumberLabel;
    private String labelError;
    private List<AnswerOption> options;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxSelectionAllowed() {
        return maxSelectionAllowed;
    }

    public void setMaxSelectionAllowed(int maxSelectionAllowed) {
        this.maxSelectionAllowed = maxSelectionAllowed;
    }

    public boolean isHasOther() {
        return hasOther;
    }

    public void setHasOther(boolean hasOther) {
        this.hasOther = hasOther;
    }

    public String getOtherRequiredCondition() {
        return otherRequiredCondition;
    }

    public void setOtherRequiredCondition(String otherRequiredCondition) {
        this.otherRequiredCondition = otherRequiredCondition;
    }

    public String getOtherHint() {
        return otherHint;
    }

    public void setOtherHint(String otherHint) {
        this.otherHint = otherHint;
    }

    public String getQuestionnaireNumber() {
        return questionnaireNumber;
    }

    public void setQuestionnaireNumber(String questionnaireNumber) {
        this.questionnaireNumber = questionnaireNumber;
    }

    public String getQuestionnaireNumberLabel() {
        return questionnaireNumberLabel;
    }

    public void setQuestionnaireNumberLabel(String questionnaireNumberLabel) {
        this.questionnaireNumberLabel = questionnaireNumberLabel;
    }

    public String getLabelError() {
        return labelError;
    }

    public void setLabelError(String labelError) {
        this.labelError = labelError;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

    public void setOptions(List<AnswerOption> options) {
        this.options = options;
    }
}