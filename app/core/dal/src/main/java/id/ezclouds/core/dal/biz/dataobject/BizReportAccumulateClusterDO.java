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
 * @version $Id: BizReportAccumulateClusterDO.java, v 0.1 2024‐10‐21 9:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_accumulate_cluster")
public class BizReportAccumulateClusterDO {

    @Id
    @Column(name = "accumulate_cluster_id")
    private String accumulateClusterId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "cluster_id")
    private String clusterId;
    @Column(name = "cluster_name")
    private String clusterName;
    @Column(name = "voter_count")
    private int voterCount;
    @Column(name = "voter_male_count")
    private int voterMaleCount;
    @Column(name = "voter_female_count")
    private int voterFemaleCount;
    @Column(name = "voter_extra_count")
    private int voterExtraCount;
    @Column(name = "voter_extra_male_count")
    private int voterExtraMaleCount;
    @Column(name = "voter_extra_female_count")
    private int voterExtraFemaleCount;
    @Column(name = "modified_time")
    private String modifiedTime;

    public String getAccumulateClusterId() {
        return accumulateClusterId;
    }

    public void setAccumulateClusterId(String accumulateClusterId) {
        this.accumulateClusterId = accumulateClusterId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public int getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(int voterCount) {
        this.voterCount = voterCount;
    }

    public int getVoterMaleCount() {
        return voterMaleCount;
    }

    public void setVoterMaleCount(int voterMaleCount) {
        this.voterMaleCount = voterMaleCount;
    }

    public int getVoterFemaleCount() {
        return voterFemaleCount;
    }

    public void setVoterFemaleCount(int voterFemaleCount) {
        this.voterFemaleCount = voterFemaleCount;
    }

    public int getVoterExtraCount() {
        return voterExtraCount;
    }

    public void setVoterExtraCount(int voterExtraCount) {
        this.voterExtraCount = voterExtraCount;
    }

    public int getVoterExtraMaleCount() {
        return voterExtraMaleCount;
    }

    public void setVoterExtraMaleCount(int voterExtraMaleCount) {
        this.voterExtraMaleCount = voterExtraMaleCount;
    }

    public int getVoterExtraFemaleCount() {
        return voterExtraFemaleCount;
    }

    public void setVoterExtraFemaleCount(int voterExtraFemaleCount) {
        this.voterExtraFemaleCount = voterExtraFemaleCount;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}