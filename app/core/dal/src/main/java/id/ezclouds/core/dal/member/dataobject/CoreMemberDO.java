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
 * @version $Id: CoreMemberDO.java, v 0.1 2024‐10‐05 2:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_member")
public class CoreMemberDO extends CoreAuditableDO {

    @Id
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "sub_org_id")
    private String subOrgId;
    @Column(name = "shard")
    private String shard;
    @Column(name = "source_id")
    private String sourceId;
    @Column(name = "referrer_id")
    private String referrerId;
    @Column(name = "roles")
    private String roles;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
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
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "phone_verified")
    private int phoneVerified;
    @Column(name = "email_verified")
    private int emailVerified;
    @Column(name = "address_verified")
    private int addressVerified;
    @Column(name = "status")
    private int status;

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

    public String getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(String subOrgId) {
        this.subOrgId = subOrgId;
    }

    public String getShard() {
        return shard;
    }

    public void setShard(String shard) {
        this.shard = shard;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isPhoneVerified() {
        return phoneVerified == 1;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified ? 1 : 0;
    }

    public boolean isEmailVerified() {
        return emailVerified == 1;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified ? 1 : 0;
    }

    public boolean isAddressVerified() {
        return addressVerified == 1;
    }

    public void setAddressVerified(boolean addressVerified) {
        this.addressVerified = addressVerified ? 1 : 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}