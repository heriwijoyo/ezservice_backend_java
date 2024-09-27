/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizSequence.java, v 0.1 2024‐09‐28 1:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreBizSequence {

    private String bizSeqId;
    private String orgId;
    private String seqBizKey;
    private Integer sequence;
    private String modifiedTime;

    public String getBizSeqId() {
        return bizSeqId;
    }

    public void setBizSeqId(String bizSeqId) {
        this.bizSeqId = bizSeqId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSeqBizKey() {
        return seqBizKey;
    }

    public void setSeqBizKey(String seqBizKey) {
        this.seqBizKey = seqBizKey;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}