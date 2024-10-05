/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVoterDO.java, v 0.1 2024‐09‐23 11:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_election_voter")
public class BizVoterDO {

    @Id
    @Column(name = "voter_id")
    private String voterId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "sub_org_id")
    private String subOrgId;
    @Column(name = "shard")
    private String shard;
    @Column(name = "source_id")
    private String sourceId;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "referrer_id")
    private String referrerId;
    @Column(name = "family_size")
    private int familySize;
    @Column(name = "family_size_male")
    private int familySizeMale;
    @Column(name = "family_size_female")
    private int familySizeFemale;
    @Column(name = "name")
    private String name;
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
    @Column(name = "id_card_number")
    private String idCardNumber;
    @Column(name = "family_card_number")
    private String familyCardNumber;
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
    @Column(name = "neighbourhood")
    private String neighbourhood;
    @Column(name = "sub_neighbourhood")
    private String subNeighbourhood;
    @Column(name = "poll_station_id")
    private String pollStationId;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "modified_time")
    private String modifiedTime;
    @Column(name = "status")
    private int status;

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
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

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public int getFamilySizeMale() {
        return familySizeMale;
    }

    public void setFamilySizeMale(int familySizeMale) {
        this.familySizeMale = familySizeMale;
    }

    public int getFamilySizeFemale() {
        return familySizeFemale;
    }

    public void setFamilySizeFemale(int familySizeFemale) {
        this.familySizeFemale = familySizeFemale;
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

    public String getPollStationId() {
        return pollStationId;
    }

    public void setPollStationId(String pollStationId) {
        this.pollStationId = pollStationId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}