/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtension.java, v 0.1 2023‐12‐31 9:16 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberExtension {

    private String memberId;
    private String orgId;
    private String shard;
    private String idCardNumber;
    private String idCardDocUrl;
    private String familyCardNumber;
    private String familyCardDocUrl;
    private String provinceId;
    private String provinceName;
    private String regencyId;
    private String regencyName;
    private String districtId;
    private String districtName;
    private String villageId;
    private String villageName;
    private String rukunWarga;
    private String rukunTetangga;
    private String tpsNumber;
    private boolean isAddressVerified;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getShard() {
        return shard;
    }

    public void setShard(String shard) {
        this.shard = shard;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardDocUrl() {
        return idCardDocUrl;
    }

    public void setIdCardDocUrl(String idCardDocUrl) {
        this.idCardDocUrl = idCardDocUrl;
    }

    public String getFamilyCardNumber() {
        return familyCardNumber;
    }

    public void setFamilyCardNumber(String familyCardNumber) {
        this.familyCardNumber = familyCardNumber;
    }

    public String getFamilyCardDocUrl() {
        return familyCardDocUrl;
    }

    public void setFamilyCardDocUrl(String familyCardDocUrl) {
        this.familyCardDocUrl = familyCardDocUrl;
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

    public String getRukunWarga() {
        return rukunWarga;
    }

    public void setRukunWarga(String rukunWarga) {
        this.rukunWarga = rukunWarga;
    }

    public String getRukunTetangga() {
        return rukunTetangga;
    }

    public void setRukunTetangga(String rukunTetangga) {
        this.rukunTetangga = rukunTetangga;
    }

    public String getTpsNumber() {
        return tpsNumber;
    }

    public void setTpsNumber(String tpsNumber) {
        this.tpsNumber = tpsNumber;
    }

    public boolean isAddressVerified() {
        return isAddressVerified;
    }

    public void setAddressVerified(boolean addressVerified) {
        isAddressVerified = addressVerified;
    }
}