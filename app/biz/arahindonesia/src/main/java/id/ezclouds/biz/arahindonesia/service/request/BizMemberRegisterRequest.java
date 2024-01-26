/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.request;

import id.ezclouds.biz.arahindonesia.model.member.BizGender;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRegisterRequest.java, v 0.1 2023‐12‐31 1:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberRegisterRequest extends BizRequest {

    private String referrerId;
    private String roles;
    private String name;
    private String nickname;
    private BizGender bizGender;
    private String dateOfBirth;
    private String phone;
    private String email;
    private String avatarUrl;
    private String idCardNumber;
    private String familyCardNumber;
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
    private String address;

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public BizGender getBizGender() {
        return bizGender;
    }

    public void setBizGender(BizGender bizGender) {
        this.bizGender = bizGender;
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

    public String getFamilyCardNumber() {
        return familyCardNumber;
    }

    public void setFamilyCardNumber(String familyCardNumber) {
        this.familyCardNumber = familyCardNumber;
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
}