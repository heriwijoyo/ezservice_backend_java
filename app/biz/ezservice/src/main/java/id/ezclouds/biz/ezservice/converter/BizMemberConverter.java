/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.converter;

import id.ezclouds.biz.ezservice.model.BizStatus;
import id.ezclouds.biz.ezservice.model.member.BizGender;
import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.model.member.MemberBase;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;

import java.util.List;
import java.util.Map;

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

        //support backward compatibility for old version app dataobject
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
        bizMember.setEducation(member.getEducation());
        bizMember.setOccupation(member.getOccupation());
        bizMember.setReligion(member.getReligion());
        bizMember.setEthnic(member.getEthnic());
        bizMember.setDateOfBirth(member.getDateOfBirth());
        bizMember.setGender(BizGender.getByCode(member.getGender()));
        bizMember.setEmail(member.getEmail());
        bizMember.setAddress(member.getAddress());
        bizMember.setPhoneVerified(member.isPhoneVerified());
        bizMember.setEmailVerified(member.isEmailVerified());
        bizMember.setAddressVerified(member.isAddressVerified());
        BizStatus bizStatus = BizStatus.getByCode(member.getMemberStatus().getCode());
        bizMember.setStatus(bizStatus);
        bizMember.setAvatarUrl(member.getAvatarUrl());
        bizMember.setCreatedTime(member.getCreatedTime());

        BizSubOrganization subOrganization = new BizSubOrganization();
        if (StringUtil.isNotBlank(member.getSubOrgId())) {
            subOrganization.setOrgId(member.getOrgId());
            subOrganization.setSubOrgId(member.getSubOrgId());
        }
        bizMember.setSubOrganization(subOrganization);

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

    public static BizMember convertSimple(CoreMember member, Map<String, String> subOrgNameMap) {
        if (member == null) { return null; }
        BizMember bizMember = new BizMember();
        bizMember.setMemberId(member.getMemberId());
        bizMember.setReferrerId(member.getReferrerId());
        bizMember.setRoles(member.getRoles());
        bizMember.setName(member.getName());
        bizMember.setNickname(member.getNickname());
        bizMember.setPhone(member.getPhone());
        bizMember.setEducation(member.getEducation());
        bizMember.setOccupation(member.getOccupation());
        bizMember.setReligion(member.getReligion());
        bizMember.setEthnic(member.getEthnic());
        bizMember.setDateOfBirth(member.getDateOfBirth());
        bizMember.setGender(BizGender.getByCode(member.getGender()));
        bizMember.setEmail(member.getEmail());
        bizMember.setAddress(member.getAddress());
        bizMember.setPhoneVerified(member.isPhoneVerified());
        bizMember.setEmailVerified(member.isEmailVerified());
        bizMember.setAddressVerified(member.isAddressVerified());
        BizStatus bizStatus = BizStatus.getByCode(member.getMemberStatus().getCode());
        bizMember.setStatus(bizStatus);
        bizMember.setAvatarUrl(member.getAvatarUrl());
        bizMember.setCreatedTime(member.getCreatedTime());

        BizSubOrganization subOrganization = new BizSubOrganization();
        if (StringUtil.isNotBlank(member.getSubOrgId())) {
            subOrganization.setOrgId(member.getOrgId());
            subOrganization.setSubOrgId(member.getSubOrgId());

            bizMember.setSubOrgId(member.getSubOrgId());
            bizMember.setSubOrgName(subOrgNameMap.get(member.getSubOrgId()));
        }

        return bizMember;
    }

    public static CoreMember convert(BizMember bizMember) {
        if (bizMember == null) { return null; }
        CoreMember coreMember = new CoreMember();
        coreMember.setOrgId(bizMember.getOrgId());
        coreMember.setSourceId("BACKOFFICE");
        coreMember.setName(bizMember.getName());
        coreMember.setReferrerId(bizMember.getReferrerId());
        coreMember.setPhone(bizMember.getPhone());
        coreMember.setEducation(bizMember.getEducation());
        coreMember.setOccupation(bizMember.getOccupation());
        coreMember.setReligion(bizMember.getReligion());
        coreMember.setEthnic(bizMember.getEthnic());
        coreMember.setEmail(bizMember.getEmail());
        coreMember.setRoles(bizMember.getRoles());
        coreMember.setGender(bizMember.getGender().getCode());
        coreMember.setDateOfBirth(bizMember.getDateOfBirth());
        coreMember.setPhoneVerified(bizMember.isPhoneVerified());
        coreMember.setEmailVerified(bizMember.isEmailVerified());
        coreMember.setAddressVerified(bizMember.isAddressVerified());
        coreMember.setCreatedTime(bizMember.getCreatedTime());
        coreMember.setModifiedTime(bizMember.getModifiedTime());
        coreMember.setMemberStatus(MemberStatus.ACTIVE);
        if (bizMember.getSubOrganization() != null) {
            coreMember.setSubOrgId(
                    bizMember.getSubOrganization().getSubOrgId()
            );
        }
        return coreMember;
    }

    public static CoreMemberExtension convertExtension(BizMember bizMember) {
        if (bizMember == null) { return null; }
        CoreMemberExtension extension = new CoreMemberExtension();
        extension.setMemberId(bizMember.getMemberId());
        extension.setOrgId(bizMember.getOrgId());
        extension.setShard(ShardUtil.getShardId(bizMember.getMemberId()));
        extension.setIdCardNumber(bizMember.getIdCardNumber());
        extension.setProvinceId(bizMember.getProvinceId());
        extension.setProvinceName(bizMember.getProvinceName());
        extension.setRegencyId(bizMember.getRegencyId());
        extension.setRegencyName(bizMember.getRegencyName());
        extension.setDistrictId(bizMember.getDistrictId());
        extension.setDistrictName(bizMember.getDistrictName());
        extension.setVillageId(bizMember.getVillageId());
        extension.setVillageName(bizMember.getVillageName());
        extension.setRukunWarga(bizMember.getRukunWarga());
        extension.setRukunTetangga(bizMember.getRukunTetangga());
        extension.setTpsNumber(bizMember.getTpsNumber());
        return extension;
    }
}