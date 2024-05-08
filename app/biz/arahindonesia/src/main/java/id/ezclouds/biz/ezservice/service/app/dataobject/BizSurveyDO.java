/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyDO.java, v 0.1 2024‐02‐15 11:26 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_survey")
public class BizSurveyDO {

    @Id
    @Column(name = "survey_id")
    private String surveyId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "period")
    private String period;

    @Column(name = "description")
    private String description;

    @Column(name = "question_version")
    private int questionVersion;

    @Column(name = "sorting")
    private int sorting;

    @Column(name = "status")
    private int status;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(int questionVersion) {
        this.questionVersion = questionVersion;
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