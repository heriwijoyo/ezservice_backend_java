/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateMember.java, v 0.1 2024‐10‐10 2:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateMember {

    private String accumulateMemberId;
    private String orgId;
    private String memberId;
    private BizAccumulateMemberKey accumulateKey;
    private String accumulateVariable;
    private int accumulateCount;
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

    public BizAccumulateMemberKey getAccumulateKey() {
        return accumulateKey;
    }

    public void setAccumulateKey(BizAccumulateMemberKey accumulateKey) {
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