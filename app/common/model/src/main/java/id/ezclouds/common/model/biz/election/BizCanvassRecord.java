/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.election;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCanvassRecord.java, v 0.1 2024‐09‐23 10:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizCanvassRecord {

    private String canvassOrderId;
    private String orgId;
    private String bizSeqCode;
    private String voterId;
    private String referrerId;
    private String firstVisitDate;
    private String firstVisitAssessment;
    private String secondVisitDate;
    private String secondVisitAssessment;
    private String thirdVisitDate;
    private String thirdVisitAssessment;
    private String createdTime;
    private String modifiedTime;

    public String getCanvassOrderId() {
        return canvassOrderId;
    }

    public void setCanvassOrderId(String canvassOrderId) {
        this.canvassOrderId = canvassOrderId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getBizSeqCode() {
        return bizSeqCode;
    }

    public void setBizSeqCode(String bizSeqCode) {
        this.bizSeqCode = bizSeqCode;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getFirstVisitDate() {
        return firstVisitDate;
    }

    public void setFirstVisitDate(String firstVisitDate) {
        this.firstVisitDate = firstVisitDate;
    }

    public String getFirstVisitAssessment() {
        return firstVisitAssessment;
    }

    public void setFirstVisitAssessment(String firstVisitAssessment) {
        this.firstVisitAssessment = firstVisitAssessment;
    }

    public String getSecondVisitDate() {
        return secondVisitDate;
    }

    public void setSecondVisitDate(String secondVisitDate) {
        this.secondVisitDate = secondVisitDate;
    }

    public String getSecondVisitAssessment() {
        return secondVisitAssessment;
    }

    public void setSecondVisitAssessment(String secondVisitAssessment) {
        this.secondVisitAssessment = secondVisitAssessment;
    }

    public String getThirdVisitDate() {
        return thirdVisitDate;
    }

    public void setThirdVisitDate(String thirdVisitDate) {
        this.thirdVisitDate = thirdVisitDate;
    }

    public String getThirdVisitAssessment() {
        return thirdVisitAssessment;
    }

    public void setThirdVisitAssessment(String thirdVisitAssessment) {
        this.thirdVisitAssessment = thirdVisitAssessment;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}