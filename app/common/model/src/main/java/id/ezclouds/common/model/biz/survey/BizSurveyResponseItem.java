/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.survey;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponseItem.java, v 0.1 2024‐08‐19 9:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSurveyResponseItem {

    private String questionId;
    private String title;
    private List<BizSurveyResponseOption> selectedOptions;
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

    public List<BizSurveyResponseOption> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<BizSurveyResponseOption> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}