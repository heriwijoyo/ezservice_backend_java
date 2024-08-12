/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberImportDO.java, v 0.1 2024‐08‐11 6:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_member_import")
public class CoreMemberImportDO {

    @Id
    @Column(name = "biz_member_id")
    private String bizMemberId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "sub_org_id")
    private String subOrgId;
    @Column(name = "source_id")
    private String sourceId;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age_group")
    private String ageGroup;
    @Column(name = "phone")
    private String phone;
    @Column(name = "education")
    private String education;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "religion")
    private String religion;
    @Column(name = "ethnic")
    private String ethnic;
    @Column(name = "id_card_number")
    private String idCardNumber;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "village_name")
    private String villageName;
    @Column(name = "rukun_warga")
    private String rukunWarga;
    @Column(name = "rukun_tetangga")
    private String rukunTetangga;
    @Column(name = "tps_number")
    private String tpsNumber;
    @Column(name = "address")
    private String address;

    public String getBizMemberId() {
        return bizMemberId;
    }

    public void setBizMemberId(String bizMemberId) {
        this.bizMemberId = bizMemberId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(String subOrgId) {
        this.subOrgId = subOrgId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}