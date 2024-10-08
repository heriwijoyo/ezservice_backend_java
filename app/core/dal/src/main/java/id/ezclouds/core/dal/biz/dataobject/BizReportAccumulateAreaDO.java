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
 * @version $Id: BizReportAccumulateAreaDO.java, v 0.1 2024‐10‐03 12:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_report_accumulate_area")
public class BizReportAccumulateAreaDO {

    @Id
    @Column(name = "accumulate_area_id")
    private String accumulateAreaId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "area_level")
    private String areaLevel;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "regency_id")
    private String regencyId;

    @Column(name = "regency_name")
    private String regencyName;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "village_id")
    private String villageId;

    @Column(name = "village_name")
    private String villageName;

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

    public String getAccumulateAreaId() {
        return accumulateAreaId;
    }

    public void setAccumulateAreaId(String accumulateAreaId) {
        this.accumulateAreaId = accumulateAreaId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getRegencyId() {
        return regencyId;
    }

    public void setRegencyId(String regencyId) {
        this.regencyId = regencyId;
    }

    public String getRegencyName() {
        return regencyName;
    }

    public void setRegencyName(String regencyName) {
        this.regencyName = regencyName;
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