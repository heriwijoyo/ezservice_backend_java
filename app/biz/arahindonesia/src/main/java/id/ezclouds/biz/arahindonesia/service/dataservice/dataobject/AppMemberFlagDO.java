/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.dataservice.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberFlagDO.java, v 0.1 2024‐02‐04 6:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_member_flag")
public class AppMemberFlagDO {

    @Id
    @Column(name = "member_flag_id")
    private String memberFlagId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "flag_code")
    private String flagCode;

    @Column(name = "flag_value")
    private String flagValue;

    @Column(name = "status")
    private int status;

    public String getMemberFlagId() {
        return memberFlagId;
    }

    public void setMemberFlagId(String memberFlagId) {
        this.memberFlagId = memberFlagId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFlagCode() {
        return flagCode;
    }

    public void setFlagCode(String flagCode) {
        this.flagCode = flagCode;
    }

    public String getFlagValue() {
        return flagValue;
    }

    public void setFlagValue(String flagValue) {
        this.flagValue = flagValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}