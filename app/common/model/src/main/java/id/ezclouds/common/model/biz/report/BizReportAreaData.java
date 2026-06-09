/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAreaData.java, v 0.1 2024‐10‐14 4:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAreaData {

    private String dataId;
    private String dataName;
    private Integer voterCount;
    private Integer voterMaleCount;
    private Integer voterFemaleCount;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(Integer voterCount) {
        this.voterCount = voterCount;
    }

    public Integer getVoterMaleCount() {
        return voterMaleCount;
    }

    public void setVoterMaleCount(Integer voterMaleCount) {
        this.voterMaleCount = voterMaleCount;
    }

    public Integer getVoterFemaleCount() {
        return voterFemaleCount;
    }

    public void setVoterFemaleCount(Integer voterFemaleCount) {
        this.voterFemaleCount = voterFemaleCount;
    }
}