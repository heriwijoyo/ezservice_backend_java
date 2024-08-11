/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.dataobject;

import javax.persistence.*;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtBackOfficeDO.java, v 0.1 2024‐08‐11 12:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_member_extension")
public class CoreMemberExtBackOfficeDO {

    @Id
    @Column(name = "member_extension_id")
    private String memberExtensionId;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "regency_name")
    private String regencyName;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "village_name")
    private String villageName;

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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getRegencyName() {
        return regencyName;
    }

    public void setRegencyName(String regencyName) {
        this.regencyName = regencyName;
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
}