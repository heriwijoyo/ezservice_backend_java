/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.dataobject;

import id.ezclouds.core.dal.CoreAuditableDO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtensionDO.java, v 0.1 2024‐10‐05 2:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_member_extension")
public class CoreMemberExtensionDO extends CoreAuditableDO {

    @Id
    @Column(name = "member_extension_id")
    private String memberExtensionId;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "shard")
    private String shard;
    @Column(name = "id_card_number")
    private String idCardNumber;
    @Column(name = "id_card_doc_url")
    private String idCardDocUrl;
    @Column(name = "family_card_number")
    private String familyCardNumber;
    @Column(name = "family_card_doc_url")
    private String familyCardDocUrl;
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
    @Column(name = "rukun_warga")
    private String neighbourhood;
    @Column(name = "rukun_tetangga")
    private String subNeighbourhood;
    @Column(name = "tps_number")
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