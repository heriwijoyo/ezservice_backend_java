/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DistrictMasterData.java, v 0.1 2024‐09‐04 10:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class DistrictMasterData {

    private String bizMasterId;
    private String districtId;
    private String districtName;
    private Integer voterMale;
    private Integer voterFemale;
    private Integer voterTotal;
    private Integer pollStationTotal;

    public String getBizMasterId() {
        return bizMasterId;
    }

    public void setBizMasterId(String bizMasterId) {
        this.bizMasterId = bizMasterId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getVoterMale() {
        return voterMale;
    }

    public void setVoterMale(Integer voterMale) {
        this.voterMale = voterMale;
    }

    public Integer getVoterFemale() {
        return voterFemale;
    }

    public void setVoterFemale(Integer voterFemale) {
        this.voterFemale = voterFemale;
    }

    public Integer getVoterTotal() {
        return voterTotal;
    }

    public void setVoterTotal(Integer voterTotal) {
        this.voterTotal = voterTotal;
    }

    public Integer getPollStationTotal() {
        return pollStationTotal;
    }

    public void setPollStationTotal(Integer pollStationTotal) {
        this.pollStationTotal = pollStationTotal;
    }
}