/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateMemberDO.java, v 0.1 2024‐10‐10 2:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_accumulate_member")
public class BizReportAccumulateMemberDO {

    @Id
    @Column(name = "accumulate_member_id")
    private String accumulateMemberId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "accumulate_key")
    private String accumulateKey;
    @Column(name = "accumulate_variable")
    private String accumulateVariable;
    @Column(name = "accumulate_count")
    private int accumulateCount;
    @Column(name = "modified_time")
    private String modifiedTime;

    public String getAccumulateMemberId() {
        return accumulateMemberId;
    }

    public void setAccumulateMemberId(String accumulateMemberId) {
        this.accumulateMemberId = accumulateMemberId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccumulateKey() {
        return accumulateKey;
    }

    public void setAccumulateKey(String accumulateKey) {
        this.accumulateKey = accumulateKey;
    }

    public String getAccumulateVariable() {
        return accumulateVariable;
    }

    public void setAccumulateVariable(String accumulateVariable) {
        this.accumulateVariable = accumulateVariable;
    }

    public int getAccumulateCount() {
        return accumulateCount;
    }

    public void setAccumulateCount(int accumulateCount) {
        this.accumulateCount = accumulateCount;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}