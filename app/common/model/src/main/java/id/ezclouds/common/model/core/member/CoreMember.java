/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.member;

import id.ezclouds.common.model.annotation.AnnotationConstant;
import id.ezclouds.common.model.annotation.PublicImageUrl;
import id.ezclouds.common.model.core.CoreStatus;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMember.java, v 0.1 2024‐10‐05 1:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMember {

    private String memberId;
    private String orgId;
    private String subOrgId;
    private String shard;
    private String sourceId;
    private String referrerId;
    private String roles;
    private String name;
    private String nickname;
    private CoreGender gender;
    private String dateOfBirth;
    private String phone;
    private String education;
    private String occupation;
    private String religion;
    private String ethnic;
    private String email;
    private String address;
    @PublicImageUrl(name = AnnotationConstant.AVATAR_URL)
    private String avatarUrl;
    private boolean phoneVerified;
    private boolean emailVerified;
    private boolean addressVerified;
    private String createdTime;
    private String modifiedTime;
    private CoreStatus status;
    private CoreMemberExtension memberExtension;

    public void initMemberExtension() {
        memberExtension = new CoreMemberExtension();
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public CoreGender getGender() {
        return gender;
    }

    public void setGender(CoreGender gender) {
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

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isAddressVerified() {
        return addressVerified;
    }

    public void setAddressVerified(boolean addressVerified) {
        this.addressVerified = addressVerified;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public CoreStatus getStatus() {
        return status;
    }

    public void setStatus(CoreStatus status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public CoreMemberExtension getMemberExtension() {
        return memberExtension;
    }

    public void setMemberExtension(CoreMemberExtension memberExtension) {
        this.memberExtension = memberExtension;
    }
}