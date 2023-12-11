/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberDO.java, v 0.1 2023‐12‐11 11:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_member")
public class AppMemberDO {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "shard")
    private String shard;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "referrer_id")
    private String referrerId;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "gender_code")
    private Integer genderCode;

    @Column(name = "gender_label")
    private String genderLabel;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_verify")
    private Integer phoneVerified;

    @Column(name = "email_verify")
    private Integer emailVerified;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "id_card_doc_image")
    private String idCardDocument;

    @Column(name = "family_card_number")
    private String familyCardNumber;

    @Column(name = "family_card_doc_image")
    private String familyCardDocument;

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
    private String rukunWarga;

    @Column(name = "rukun_tetangga")
    private String rukunTetangga;

    @Column(name = "tps_number")
    private String tpsNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "address_verify")
    private Integer addressVerified;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "status")
    private Integer status;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getShard() {
        return shard;
    }

    public void setShard(String shard) {
        this.shard = shard;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(String genderLabel) {
        this.genderLabel = genderLabel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(Integer phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Integer getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Integer emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardDocument() {
        return idCardDocument;
    }

    public void setIdCardDocument(String idCardDocument) {
        this.idCardDocument = idCardDocument;
    }

    public String getFamilyCardNumber() {
        return familyCardNumber;
    }

    public void setFamilyCardNumber(String familyCardNumber) {
        this.familyCardNumber = familyCardNumber;
    }

    public String getFamilyCardDocument() {
        return familyCardDocument;
    }

    public void setFamilyCardDocument(String familyCardDocument) {
        this.familyCardDocument = familyCardDocument;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddressVerified() {
        return addressVerified;
    }

    public void setAddressVerified(Integer addressVerified) {
        this.addressVerified = addressVerified;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}