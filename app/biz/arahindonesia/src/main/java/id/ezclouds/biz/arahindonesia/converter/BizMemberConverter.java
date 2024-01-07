/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.biz.arahindonesia.model.member.BizGender;
import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.common.dal.model.AppMemberDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberConverter.java, v 0.1 2023‐12‐11 9:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberConverter {

    public static MemberBase convert(AppMemberDO memberDO) {
        if (memberDO == null) { return null; }

        MemberBase memberBase = new MemberBase();
        memberBase.setMemberId(memberDO.getMemberId());
        memberBase.setReferrerId(memberDO.getReferrerId());
        memberBase.setRole(memberDO.getRole());
        memberBase.setName(memberDO.getName());
        memberBase.setNickname(memberDO.getNickname());
        memberBase.setGenderCode(memberDO.getGenderCode());
        memberBase.setGenderLabel(memberDO.getGenderLabel());
        memberBase.setDateOfBirth(memberDO.getDateOfBirth());
        memberBase.setPhone(memberDO.getPhone());
        memberBase.setEmail(memberDO.getEmail());
        memberBase.setPhoneVerified(memberDO.getPhoneVerified());
        memberBase.setEmailVerified(memberDO.getEmailVerified());
        memberBase.setAddress(memberDO.getAddress());
        memberBase.setStatus(memberDO.getStatus());

        return memberBase;
    }

    public static BizMember convert(CoreMember member, CoreMemberExtension extension) {
        if (member == null) { return null; }
        BizMember bizMember = new BizMember();
        bizMember.setMemberId(member.getMemberId());
        bizMember.setRoles(member.getRoles());
        bizMember.setName(member.getName());
        bizMember.setNickname(member.getNickname());
        bizMember.setPhone(member.getPhone());
        bizMember.setDateOfBirth(member.getDateOfBirth());
        bizMember.setGender(BizGender.getByCode(member.getGender()));
        bizMember.setEmail(member.getEmail());
        bizMember.setAddress(member.getAddress());
        return bizMember;
    }
}