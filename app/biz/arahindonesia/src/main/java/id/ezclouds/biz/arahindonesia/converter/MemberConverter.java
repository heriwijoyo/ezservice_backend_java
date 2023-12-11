/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.common.dal.model.AppMemberDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberConverter.java, v 0.1 2023‐12‐11 9:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberConverter {

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
}