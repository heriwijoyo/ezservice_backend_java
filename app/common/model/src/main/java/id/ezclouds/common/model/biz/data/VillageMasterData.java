/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VillageMasterData.java, v 0.1 2024‐09‐04 6:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class VillageMasterData {

    private String bizMasterId;
    private String villageId;
    private String villageName;
    private String districtId;
    private String regencyId;
    private String provinceId;
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

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getRegencyId() {
        return regencyId;
    }

    public void setRegencyId(String regencyId) {
        this.regencyId = regencyId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}