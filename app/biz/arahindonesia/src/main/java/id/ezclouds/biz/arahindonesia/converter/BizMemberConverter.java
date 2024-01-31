/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.biz.arahindonesia.model.BizStatus;
import id.ezclouds.biz.arahindonesia.model.member.BizGender;
import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberConverter.java, v 0.1 2023‐12‐11 9:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberConverter {

    public static MemberBase convert(CoreMember coreMember) {
        if (coreMember == null) { return null; }

        MemberBase memberBase = new MemberBase();
        memberBase.setMemberId(coreMember.getMemberId());
        memberBase.setReferrerId(coreMember.getReferrerId());
        memberBase.setName(coreMember.getName());
        memberBase.setNickname(coreMember.getNickname());
        memberBase.setDateOfBirth(coreMember.getDateOfBirth());
        memberBase.setPhone(coreMember.getPhone());
        memberBase.setEmail(coreMember.getEmail());
        memberBase.setAddress(coreMember.getAddress());

        //support backward compatibility for old version data model
        memberBase.setRole(coreMember.getRoles());

        if ("MALE".equals(coreMember.getGender())) {
            memberBase.setGenderCode(0);
            memberBase.setGenderLabel("Laki - Laki");
        } else {
            memberBase.setGenderCode(1);
            memberBase.setGenderLabel("Perempuan");
        }

        memberBase.setPhoneVerified(coreMember.isPhoneVerified() ? 1 : 0);
        memberBase.setEmailVerified(coreMember.isEmailVerified() ? 1 : 0);
        memberBase.setStatus(coreMember.getMemberStatus().getCode());

        return memberBase;
    }

    public static BizMember convert(CoreMember member, CoreMemberExtension extension) {
        if (member == null) { return null; }
        BizMember bizMember = new BizMember();
        bizMember.setMemberId(member.getMemberId());
        bizMember.setReferrerId(member.getReferrerId());
        bizMember.setRoles(member.getRoles());
        bizMember.setName(member.getName());
        bizMember.setNickname(member.getNickname());
        bizMember.setPhone(member.getPhone());
        bizMember.setDateOfBirth(member.getDateOfBirth());
        bizMember.setGender(BizGender.getByCode(member.getGender()));
        bizMember.setEmail(member.getEmail());
        bizMember.setAddress(member.getAddress());
        bizMember.setPhoneVerified(member.isPhoneVerified());
        bizMember.setEmailVerified(member.isEmailVerified());
        bizMember.setAddressVerified(member.isAddressVerified());
        BizStatus bizStatus = BizStatus.getByCode(member.getMemberStatus().getCode());
        bizMember.setStatus(bizStatus);

        //memberExt
        if (extension == null) {
            return bizMember;
        }
        bizMember.setIdCardNumber(extension.getIdCardNumber());
        bizMember.setIdCardDocUrl(extension.getIdCardDocUrl());
        bizMember.setFamilyCardNumber(extension.getFamilyCardNumber());
        bizMember.setFamilyCardDocUrl(extension.getFamilyCardDocUrl());
        bizMember.setProvinceId(extension.getProvinceId());
        bizMember.setProvinceName(extension.getProvinceName());
        bizMember.setRegencyId(extension.getRegencyId());
        bizMember.setRegencyName(extension.getRegencyName());
        bizMember.setDistrictId(extension.getDistrictId());
        bizMember.setDistrictName(extension.getDistrictName());
        bizMember.setVillageId(extension.getVillageId());
        bizMember.setVillageName(extension.getVillageName());
        bizMember.setRukunWarga(extension.getRukunWarga());
        bizMember.setRukunTetangga(extension.getRukunTetangga());
        bizMember.setTpsNumber(extension.getTpsNumber());
        return bizMember;
    }
}