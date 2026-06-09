/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateArea.java, v 0.1 2024‐10‐03 12:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizReportAccumulateArea {

    private String accumulateAreaId;
    private String orgId;
    private CoreAreaLevel areaLevel;
    private String provinceId;
    private String provinceName;
    private String regencyId;
    private String regencyName;
    private String districtId;
    private String districtName;
    private String villageId;
    private String villageName;
    private int voterCount;
    private int voterMaleCount;
    private int voterFemaleCount;
    private int voterExtraCount;
    private int voterExtraMaleCount;
    private int voterExtraFemaleCount;
    private String modifiedTime;

    public BizReportAccumulateArea() {
    }

    public BizReportAccumulateArea(CoreArea coreArea) {
        setAreaLevel(coreArea.getAreaLevel());

        String areaId = coreArea.getAreaId();
        String areaName = coreArea.getName();
        String areaParentId = coreArea.getParentId();
        switch (coreArea.getAreaLevel()) {
            case PROVINCE:
                setProvinceId(areaId);
                setProvinceName(areaName);
                break;
            case REGENCY:
                setRegencyId(areaId);
                setRegencyName(areaName);
                setProvinceId(areaParentId);
                break;
            case DISTRICT:
                setDistrictId(areaId);
                setDistrictName(areaName);
                setRegencyId(areaParentId);
                break;
            case VILLAGE:
                setVillageId(areaId);
                setVillageName(areaName);
                setDistrictId(areaParentId);
                break;
        }
        setVoterCount(0);
        setVoterMaleCount(0);
        setVoterFemaleCount(0);
        setVoterExtraCount(0);

        String accumulateId = HashUtil.createHash(orgId, areaLevel.getCode(), getLevelId());
        setAccumulateAreaId(accumulateId);
    }

    public String getAccumulateAreaId() {
        return accumulateAreaId;
    }

    private String getLevelId() {
        switch (areaLevel) {
            case PROVINCE:
                return provinceId;
            case REGENCY:
                return regencyId;
            case DISTRICT:
                return districtId;
            case VILLAGE:
                return villageId;
        }
        return StringUtil.EMPTY;
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

    public CoreAreaLevel getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(CoreAreaLevel areaLevel) {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}