/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateCluster.java, v 0.1 2024‐10‐21 9:26 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateCluster {

    private String accumulateClusterId;
    private String orgId;
    private String clusterId;
    private String clusterName;
    private int voterCount;
    private int voterMaleCount;
    private int voterFemaleCount;
    private int voterExtraCount;
    private int voterExtraMaleCount;
    private int voterExtraFemaleCount;
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