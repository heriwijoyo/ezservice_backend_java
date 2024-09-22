/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.processor.repo;

import id.ezclouds.biz.election.model.annotation.InjectedValue;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyBaseData.java, v 0.1 2024‐05‐12 10:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@MappedSuperclass
public class AppSurveyBaseData {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "survey_id")
    private String surveyId;
    @Column(name = "response_id")
    private String responseId;
    @Column(name = "submitter_id")
    private String submitterId;
    @Column(name = "question_version")
    private String questionVersion;

    @Column(name = "q_001_answer")
    @InjectedValue
    private String q_001_answer;
    @Column(name = "q_001_other")
    @InjectedValue
    private String q_001_other;

    @Column(name = "q_002_answer")
    @InjectedValue
    private String q_002_answer;
    @Column(name = "q_002_other")
    @InjectedValue
    private String q_002_other;

    @Column(name = "q_003_answer")
    @InjectedValue
    private String q_003_answer;
    @Column(name = "q_003_other")
    @InjectedValue
    private String q_003_other;

    @Column(name = "q_004_answer")
    @InjectedValue
    private String q_004_answer;
    @Column(name = "q_004_other")
    @InjectedValue
    private String q_004_other;

    @Column(name = "q_005_answer")
    @InjectedValue
    private String q_005_answer;
    @Column(name = "q_005_other")
    @InjectedValue
    private String q_005_other;

    @Column(name = "q_006_answer")
    @InjectedValue
    private String q_006_answer;
    @Column(name = "q_006_other")
    @InjectedValue
    private String q_006_other;

    @Column(name = "q_007_answer")
    @InjectedValue
    private String q_007_answer;
    @Column(name = "q_007_other")
    @InjectedValue
    private String q_007_other;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

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

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public String getQ_001_answer() {
        return q_001_answer;
    }

    public void setQ_001_answer(String q_001_answer) {
        this.q_001_answer = q_001_answer;
    }

    public String getQ_001_other() {
        return q_001_other;
    }

    public void setQ_001_other(String q_001_other) {
        this.q_001_other = q_001_other;
    }

    public String getQ_002_answer() {
        return q_002_answer;
    }

    public void setQ_002_answer(String q_002_answer) {
        this.q_002_answer = q_002_answer;
    }

    public String getQ_002_other() {
        return q_002_other;
    }

    public void setQ_002_other(String q_002_other) {
        this.q_002_other = q_002_other;
    }

    public String getQ_003_answer() {
        return q_003_answer;
    }

    public void setQ_003_answer(String q_003_answer) {
        this.q_003_answer = q_003_answer;
    }

    public String getQ_003_other() {
        return q_003_other;
    }

    public void setQ_003_other(String q_003_other) {
        this.q_003_other = q_003_other;
    }

    public String getQ_004_answer() {
        return q_004_answer;
    }

    public void setQ_004_answer(String q_004_answer) {
        this.q_004_answer = q_004_answer;
    }

    public String getQ_004_other() {
        return q_004_other;
    }

    public void setQ_004_other(String q_004_other) {
        this.q_004_other = q_004_other;
    }

    public String getQ_005_answer() {
        return q_005_answer;
    }

    public void setQ_005_answer(String q_005_answer) {
        this.q_005_answer = q_005_answer;
    }

    public String getQ_005_other() {
        return q_005_other;
    }

    public void setQ_005_other(String q_005_other) {
        this.q_005_other = q_005_other;
    }

    public String getQ_006_answer() {
        return q_006_answer;
    }

    public void setQ_006_answer(String q_006_answer) {
        this.q_006_answer = q_006_answer;
    }

    public String getQ_006_other() {
        return q_006_other;
    }

    public void setQ_006_other(String q_006_other) {
        this.q_006_other = q_006_other;
    }

    public String getQ_007_answer() {
        return q_007_answer;
    }

    public void setQ_007_answer(String q_007_answer) {
        this.q_007_answer = q_007_answer;
    }

    public String getQ_007_other() {
        return q_007_other;
    }

    public void setQ_007_other(String q_007_other) {
        this.q_007_other = q_007_other;
    }
}