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
}