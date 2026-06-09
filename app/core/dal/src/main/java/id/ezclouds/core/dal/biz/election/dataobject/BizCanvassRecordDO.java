/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCanvassRecordDO.java, v 0.1 2024‐09‐26 12:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_election_canvass_record")
public class BizCanvassRecordDO {

    @Id
    @Column(name = "canvass_order_id")
    private String canvassOrderId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "biz_seq_code")
    private String bizSeqCode;
    @Column(name = "voter_id")
    private String voterId;
    @Column(name = "referrer_id")
    private String referrerId;
    @Column(name = "first_visit_date")
    private String firstVisitDate;
    @Column(name = "first_visit_assessment")
    private String firstVisitAssessment;
    @Column(name = "second_visit_date")
    private String secondVisitDate;
    @Column(name = "second_visit_assessment")
    private String secondVisitAssessment;
    @Column(name = "third_visit_date")
    private String thirdVisitDate;
    @Column(name = "third_visit_assessment")
    private String thirdVisitAssessment;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "modified_time")
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