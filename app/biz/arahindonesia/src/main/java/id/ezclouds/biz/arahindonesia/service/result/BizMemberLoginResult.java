/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.result;

import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberLoginResult.java, v 0.1 2023‐12‐11 2:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberLoginResult {

    private String memberSessionId;
    private String memberSessionCode;
    private String memberRoleCode;
    private String successMessage;
    private MemberBase memberBase;
    private BizMember bizMember;

    public String getMemberSessionId() {
        return memberSessionId;
    }

    public void setMemberSessionId(String memberSessionId) {
        this.memberSessionId = memberSessionId;
    }

    public String getMemberSessionCode() {
        return memberSessionCode;
    }

    public void setMemberSessionCode(String memberSessionCode) {
        this.memberSessionCode = memberSessionCode;
    }

    public String getMemberRoleCode() {
        return memberRoleCode;
    }

    public void setMemberRoleCode(String memberRoleCode) {
        this.memberRoleCode = memberRoleCode;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public MemberBase getMemberBase() {
        return memberBase;
    }

    public void setMemberBase(MemberBase memberBase) {
        this.memberBase = memberBase;
    }

    public BizMember getBizMember() {
        return bizMember;
    }

    public void setBizMember(BizMember bizMember) {
        this.bizMember = bizMember;
    }
}