/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.dataobject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponseDO.java, v 0.1 2024‐02‐18 1:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_survey_response")
public class BizSurveyResponseDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "survey_id")
    private String surveyId;

    @Column(name = "submitter_member_id")
    private String submitterMemberId;

    @Column(name = "question_version")
    private String questionVersion;

    @Column(name = "responder_data")
    private String responderData;

    @Column(name = "response_data")
    private String responseData;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "process_id")
    private String processId;

    @Column(name = "process_time")
    private String processTime;

    @Column(name = "process_message")
    private String processMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getSubmitterMemberId() {
        return submitterMemberId;
    }

    public void setSubmitterMemberId(String submitterMemberId) {
        this.submitterMemberId = submitterMemberId;
    }

    public String getQuestionVersion() {
        return questionVersion;
    }

    public void setQuestionVersion(String questionVersion) {
        this.questionVersion = questionVersion;
    }

    public String getResponderData() {
        return responderData;
    }

    public void setResponderData(String responderData) {
        this.responderData = responderData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getProcessMessage() {
        return processMessage;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}