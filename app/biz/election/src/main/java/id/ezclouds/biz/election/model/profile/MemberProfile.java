/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.profile;

import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.biz.election.model.member.MemberBase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberProfile.java, v 0.1 2023‐12‐11 12:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberProfile {

    private Map<String, String> appProfiles = new HashMap<>();
    private MemberBase memberBase;
    private BizMember bizMember;

    public Map<String, String> getAppProfiles() {
        return appProfiles;
    }

    public void setAppProfiles(Map<String, String> appProfiles) {
        this.appProfiles = appProfiles;
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