/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.util;

import id.ezclouds.common.util.BoolState;
import id.ezclouds.core.member.dataobject.EzCoreMemberDO;
import id.ezclouds.core.member.dataobject.EzCoreMemberExtensionDO;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberConverter.java, v 0.1 2023‐12‐31 11:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberConverter {

    public static CoreMember convert(EzCoreMemberDO memberDO) {
        if (memberDO == null) { return null; }
        CoreMember member = new CoreMember();
        member.setMemberId(memberDO.getMemberId());
        member.setReferrerId(memberDO.getReferrerId());
        member.setOrgId(memberDO.getOrgId());
        member.setSubOrgId(memberDO.getSubOrgId());
        member.setShard(memberDO.getShard());
        member.setName(memberDO.getName());
        member.setNickname(memberDO.getNickname());
        member.setReferrerId(memberDO.getReferrerId());
        member.setRoles(memberDO.getRoles());
        member.setGender(memberDO.getGender());
        member.setDateOfBirth(memberDO.getDateOfBirth());
        member.setPhone(memberDO.getPhone());
        member.setEducation(memberDO.getEducation());
        member.setOccupation(memberDO.getOccupation());
        member.setReligion(memberDO.getReligion());
        member.setEthnic(memberDO.getEthnic());
        member.setEmail(memberDO.getEmail());
        member.setAvatarUrl(memberDO.getAvatarUrl());
        member.setAddress(memberDO.getAddress());
        member.setPhoneVerified(BoolState.getBool(memberDO.getPhoneVerified()));
        member.setEmailVerified(BoolState.getBool(memberDO.getEmailVerified()));
        member.setAddressVerified(BoolState.getBool(memberDO.getIsAddressVerified()));
        member.setMemberStatus(MemberStatus.getByCode(memberDO.getStatus()));
        member.setCreatedTime(memberDO.getCreatedTime());
        return member;
    }

    public static EzCoreMemberDO convert(CoreMember coreMember) {
        if (coreMember == null) { return null; }
        EzCoreMemberDO memberDO = new EzCoreMemberDO();
        memberDO.setMemberId(coreMember.getMemberId());
        memberDO.setOrgId(coreMember.getOrgId());
        memberDO.setSubOrgId(coreMember.getSubOrgId());
        memberDO.setShard(coreMember.getShard());
        memberDO.setSourceId(coreMember.getSourceId());
        memberDO.setReferrerId(coreMember.getReferrerId());
        memberDO.setRoles(coreMember.getRoles());
        memberDO.setName(coreMember.getName());
        memberDO.setNickname(coreMember.getNickname());
        memberDO.setGender(coreMember.getGender());
        memberDO.setDateOfBirth(coreMember.getDateOfBirth());
        memberDO.setPhone(coreMember.getPhone());
        memberDO.setEducation(coreMember.getEducation());
        memberDO.setOccupation(coreMember.getOccupation());
        memberDO.setReligion(coreMember.getReligion());
        memberDO.setEthnic(coreMember.getEthnic());
        memberDO.setEmail(coreMember.getEmail());
        memberDO.setAvatarUrl(coreMember.getAvatarUrl());
        memberDO.setAddress(coreMember.getAddress());
        memberDO.setPhoneVerified(BoolState.getState(coreMember.isPhoneVerified()));
        memberDO.setEmailVerified(BoolState.getState(coreMember.isEmailVerified()));
        memberDO.setCreatedTime(coreMember.getCreatedTime());
        memberDO.setModifiedTime(coreMember.getModifiedTime());
        memberDO.setStatus(coreMember.getMemberStatus().getCode());
        return memberDO;
    }

    public static CoreMemberExtension convert(EzCoreMemberExtensionDO extensionDO) {
        if (extensionDO == null) { return null; }
        CoreMemberExtension extension = new CoreMemberExtension();
        extension.setMemberId(extensionDO.getMemberId());
        extension.setShard(extensionDO.getShard());
        extension.setOrgId(extensionDO.getOrgId());
        extension.setIdCardNumber(extensionDO.getIdCardNumber());
        extension.setIdCardDocUrl(extensionDO.getIdCardDocUrl());
        extension.setFamilyCardNumber(extensionDO.getFamilyCardNumber());
        extension.setFamilyCardDocUrl(extensionDO.getFamilyCardDocUrl());
        extension.setProvinceId(extensionDO.getProvinceId());
        extension.setProvinceName(extensionDO.getProvinceName());
        extension.setRegencyId(extensionDO.getRegencyId());
        extension.setRegencyName(extensionDO.getRegencyName());
        extension.setDistrictId(extensionDO.getDistrictId());
        extension.setDistrictName(extensionDO.getDistrictName());
        extension.setVillageId(extensionDO.getVillageId());
        extension.setVillageName(extensionDO.getVillageName());
        extension.setRukunWarga(extensionDO.getRukunWarga());
        extension.setRukunTetangga(extensionDO.getRukunTetangga());
        extension.setTpsNumber(extensionDO.getTpsNumber());
        return extension;
    }

    public static EzCoreMemberExtensionDO convert(CoreMemberExtension extension) {
        if (extension == null) { return null; }
        EzCoreMemberExtensionDO extensionDO = new EzCoreMemberExtensionDO();
        extensionDO.setMemberId(extension.getMemberId());
        extensionDO.setOrgId(extension.getOrgId());
        extensionDO.setShard(extension.getShard());
        extensionDO.setIdCardNumber(extension.getIdCardNumber());
        extensionDO.setIdCardDocUrl(extension.getIdCardDocUrl());
        extensionDO.setFamilyCardNumber(extension.getFamilyCardNumber());
        extensionDO.setFamilyCardDocUrl(extension.getFamilyCardDocUrl());
        extensionDO.setProvinceId(extension.getProvinceId());
        extensionDO.setProvinceName(extension.getProvinceName());
        extensionDO.setRegencyId(extension.getRegencyId());
        extensionDO.setRegencyName(extension.getRegencyName());
        extensionDO.setDistrictId(extension.getDistrictId());
        extensionDO.setDistrictName(extension.getDistrictName());
        extensionDO.setVillageId(extension.getVillageId());
        extensionDO.setVillageName(extension.getVillageName());
        extensionDO.setRukunWarga(extension.getRukunWarga());
        extensionDO.setRukunTetangga(extension.getRukunTetangga());
        extensionDO.setTpsNumber(extension.getTpsNumber());
        return extensionDO;
    }
}