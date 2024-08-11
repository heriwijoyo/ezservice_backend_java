/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.member;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberBackOffice.java, v 0.1 2024‐08‐10 11:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberBackOffice {

    private String memberId;
    private String memberName;
    private String subOrgName;
    private String idCardNumber;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getSubOrgName() {
        return subOrgName;
    }

    public void setSubOrgName(String subOrgName) {
        this.subOrgName = subOrgName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
}