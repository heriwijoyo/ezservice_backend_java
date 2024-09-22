/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyQuestionDO.java, v 0.1 2024‐02‐15 11:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_survey_question")
public class BizSurveyQuestionDO {

    @Id
    @Column(name = "qid")
    private String qid;

    @Column(name = "survey_id")
    private String surveyId;

    @Column(name = "required")
    private int required;

    @Column(name = "title")
    private String title;

    @Column(name = "max_selection_allowed")
    private int maxSelectionAllowed;

    @Column(name = "has_other")
    private int hasOther;

    @Column(name = "other_required_condition")
    private String otherRequiredCondition;

    @Column(name = "other_hint")
    private String otherHint;

    @Column(name = "sorting")
    private int sorting;

    @Column(name = "status")
    private int status;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
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

    public int getHasOther() {
        return hasOther;
    }

    public void setHasOther(int hasOther) {
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

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}