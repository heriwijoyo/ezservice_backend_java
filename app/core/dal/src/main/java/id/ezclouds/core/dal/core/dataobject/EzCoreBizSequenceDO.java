/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizSequenceDO.java, v 0.1 2024‐09‐28 1:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_biz_sequence")
public class EzCoreBizSequenceDO {

    @Id
    @Column(name = "biz_seq_id")
    private String bizSeqId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "seq_biz_key")
    private String seqBizKey;
    @Column(name = "sequence")
    private Integer sequence;
    @Column(name = "modified_time")
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