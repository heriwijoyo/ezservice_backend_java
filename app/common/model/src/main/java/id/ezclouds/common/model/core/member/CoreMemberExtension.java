/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.member;

import id.ezclouds.common.model.annotation.AnnotationConstant;
import id.ezclouds.common.model.annotation.PublicImageUrl;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtension.java, v 0.1 2024‐10‐05 1:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberExtension {

    private String memberExtensionId;
    private String memberId;
    private String orgId;
    private String shard;
    private String idCardNumber;
    @PublicImageUrl(name = AnnotationConstant.IDCARD_URL)
    private String idCardDocUrl;
    private String familyCardNumber;
    @PublicImageUrl(name = AnnotationConstant.FAMCARD_URL)
    private String familyCardDocUrl;
    private String provinceId;
    private String provinceName;
    private String regencyId;
    private String regencyName;
    private String districtId;
    private String districtName;
    private String villageId;
    private String villageName;
    private String neighbourhood;
    private String subNeighbourhood;
    private String voteStation;

    public String getMemberExtensionId() {
        return memberExtensionId;
    }

    public void setMemberExtensionId(String memberExtensionId) {
        this.memberExtensionId = memberExtensionId;
    }

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

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getSubNeighbourhood() {
        return subNeighbourhood;
    }

    public void setSubNeighbourhood(String subNeighbourhood) {
        this.subNeighbourhood = subNeighbourhood;
    }

    public String getVoteStation() {
        return voteStation;
    }

    public void setVoteStation(String voteStation) {
        this.voteStation = voteStation;
    }
}