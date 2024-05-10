/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.survey;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: QuestionnaireData.java, v 0.1 2024‐05‐10 3:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class QuestionnaireData {

    private String questionId;
    private String title;
    private List<AnswerOption> selectedOptions;
    private String other;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerOption> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<AnswerOption> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}