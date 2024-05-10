/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor.repo;

import id.ezclouds.biz.ezservice.model.annotation.InjectedValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyDataRJL001.java, v 0.1 2024‐05‐10 3:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_survey_data_rjl001")
public class AppSurveyDataRJL001 {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "response_id")
    private String responseId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "submitter_id")
    private String submitterId;
    @Column(name = "question_version")
    private String questionVersion;
    @Column(name = "name")
    @InjectedValue(field = "name")
    private String name;
    @Column(name = "position")
    private String position;
    @Column(name = "voter_size")
    @InjectedValue(type = Integer.class, field = "voterSize")
    private int voterSize;
    @Column(name = "education")
    private String education;
    @Column(name = "gender")
    private String gender;
    @Column(name = "religion")
    private String religion;
    @Column(name = "ethnic")
    private String ethnic;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    @InjectedValue(type = Integer.class, field = "age")
    private int age;
    @Column(name = "q01_a")
    private String q01Answer;
    @Column(name = "q01_o")
    private String q01Other;
    @Column(name = "q02_a")
    private String q02Answer;
    @Column(name = "q02_o")
    private String q02Other;
    @Column(name = "q03_a")
    private String q03Answer;
    @Column(name = "q03_o")
    private String q03Other;
    @Column(name = "q04_a")
    private String q04Answer;
    @Column(name = "q04_o")
    private String q04Other;
    @Column(name = "q05_a")
    private String q05Answer;
    @Column(name = "q05_o")
    private String q05Other;
    @Column(name = "q06_a")
    private String q06Answer;
    @Column(name = "q06_o")
    private String q06Other;
    @Column(name = "q07_a")
    private String q07Answer;
    @Column(name = "q07_o")
    private String q07Other;
    @Column(name = "q08_a")
    private String q08Answer;
    @Column(name = "q08_o")
    private String q08Other;
    @Column(name = "q09_a")
    private String q09Answer;
    @Column(name = "q09_o")
    private String q09Other;
    @Column(name = "q10_a")
    private String q10Answer;
    @Column(name = "q10_o")
    private String q10Other;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getVoterSize() {
        return voterSize;
    }

    public void setVoterSize(int voterSize) {
        this.voterSize = voterSize;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getQ01Answer() {
        return q01Answer;
    }

    public void setQ01Answer(String q01Answer) {
        this.q01Answer = q01Answer;
    }

    public String getQ01Other() {
        return q01Other;
    }

    public void setQ01Other(String q01Other) {
        this.q01Other = q01Other;
    }

    public String getQ02Answer() {
        return q02Answer;
    }

    public void setQ02Answer(String q02Answer) {
        this.q02Answer = q02Answer;
    }

    public String getQ02Other() {
        return q02Other;
    }

    public void setQ02Other(String q02Other) {
        this.q02Other = q02Other;
    }

    public String getQ03Answer() {
        return q03Answer;
    }

    public void setQ03Answer(String q03Answer) {
        this.q03Answer = q03Answer;
    }

    public String getQ03Other() {
        return q03Other;
    }

    public void setQ03Other(String q03Other) {
        this.q03Other = q03Other;
    }

    public String getQ04Answer() {
        return q04Answer;
    }

    public void setQ04Answer(String q04Answer) {
        this.q04Answer = q04Answer;
    }

    public String getQ04Other() {
        return q04Other;
    }

    public void setQ04Other(String q04Other) {
        this.q04Other = q04Other;
    }

    public String getQ05Answer() {
        return q05Answer;
    }

    public void setQ05Answer(String q05Answer) {
        this.q05Answer = q05Answer;
    }

    public String getQ05Other() {
        return q05Other;
    }

    public void setQ05Other(String q05Other) {
        this.q05Other = q05Other;
    }

    public String getQ06Answer() {
        return q06Answer;
    }

    public void setQ06Answer(String q06Answer) {
        this.q06Answer = q06Answer;
    }

    public String getQ06Other() {
        return q06Other;
    }

    public void setQ06Other(String q06Other) {
        this.q06Other = q06Other;
    }

    public String getQ07Answer() {
        return q07Answer;
    }

    public void setQ07Answer(String q07Answer) {
        this.q07Answer = q07Answer;
    }

    public String getQ07Other() {
        return q07Other;
    }

    public void setQ07Other(String q07Other) {
        this.q07Other = q07Other;
    }

    public String getQ08Answer() {
        return q08Answer;
    }

    public void setQ08Answer(String q08Answer) {
        this.q08Answer = q08Answer;
    }

    public String getQ08Other() {
        return q08Other;
    }

    public void setQ08Other(String q08Other) {
        this.q08Other = q08Other;
    }

    public String getQ09Answer() {
        return q09Answer;
    }

    public void setQ09Answer(String q09Answer) {
        this.q09Answer = q09Answer;
    }

    public String getQ09Other() {
        return q09Other;
    }

    public void setQ09Other(String q09Other) {
        this.q09Other = q09Other;
    }

    public String getQ10Answer() {
        return q10Answer;
    }

    public void setQ10Answer(String q10Answer) {
        this.q10Answer = q10Answer;
    }

    public String getQ10Other() {
        return q10Other;
    }

    public void setQ10Other(String q10Other) {
        this.q10Other = q10Other;
    }
}