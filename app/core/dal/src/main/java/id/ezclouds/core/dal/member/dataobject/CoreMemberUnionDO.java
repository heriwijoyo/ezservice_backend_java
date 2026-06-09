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
 * @version $Id: CoreMemberUnionDO.java, v 0.1 2024‐07‐28 6:19 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "biz_member_union")
public class CoreMemberUnionDO {

    @Id
    @Column(name = "biz_union_id")
    private String bizUnionId;
    @Column(name = "source")
    private String source;
    @Column(name = "source_id")
    private String sourceId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "sub_org_id")
    private String subOrgId;
    @Column(name = "sub_org_name")
    private String subOrgName;
    @Column(name = "role")
    private String role;
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
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "created_time")
    private String createdTime;

    public String getBizUnionId() {
        return bizUnionId;
    }

    public String getSource() {
        return source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getSubOrgId() {
        return subOrgId;
    }

    public String getSubOrgName() {
        return subOrgName;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getEducation() {
        return education;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getReligion() {
        return religion;
    }

    public String getEthnic() {
        return ethnic;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getVillageName() {
        return villageName;
    }

    public String getRukunWarga() {
        return rukunWarga;
    }

    public String getRukunTetangga() {
        return rukunTetangga;
    }

    public String getTpsNumber() {
        return tpsNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}