/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.inner.converter;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.model.MemberStatus;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRequestConverter.java, v 0.1 2024‐02‐04 10:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberRequestConverter {

    public static CoreMember getCoreMember(BizMemberRegisterRequest request) {
        CoreMember coreMember = new CoreMember();
        coreMember.setSubOrgId(request.getSubOrgId());
        coreMember.setSourceId(request.getExtendInfo().get(AppConstant.ExtKey.SOURCE_ID));
        coreMember.setReferrerId(request.getReferrerId());
        coreMember.setRoles(request.getRoles());
        coreMember.setName(request.getName());
        coreMember.setNickname(request.getNickname());
        coreMember.setGender(request.getBizGender().getCode());
        coreMember.setDateOfBirth(request.getDateOfBirth());
        coreMember.setPhone(request.getPhone());
        coreMember.setEmail(request.getEmail());
        coreMember.setAvatarUrl(request.getAvatarUrl());
        coreMember.setAddress(request.getAddress());
        coreMember.setCreatedTime(DateUtil.getCurrentFormattedDate());
        coreMember.setModifiedTime(DateUtil.getCurrentFormattedDate());
        coreMember.setMemberStatus(MemberStatus.ACTIVE);
        return coreMember;
    }

    public static CoreMemberExtension getCoreMemberExt(BizMemberRegisterRequest request) {
        CoreMemberExtension memberExtension = new CoreMemberExtension();
        memberExtension.setIdCardNumber(request.getIdCardNumber());
        memberExtension.setFamilyCardNumber(request.getFamilyCardNumber());
        memberExtension.setProvinceId(request.getProvinceId());
        memberExtension.setProvinceName(request.getProvinceName());
        memberExtension.setRegencyId(request.getRegencyId());
        memberExtension.setRegencyName(request.getRegencyName());
        memberExtension.setDistrictId(request.getDistrictId());
        memberExtension.setDistrictName(request.getDistrictName());
        memberExtension.setVillageId(request.getVillageId());
        memberExtension.setVillageName(request.getVillageName());
        memberExtension.setRukunWarga(request.getRukunWarga());
        memberExtension.setRukunTetangga(request.getRukunTetangga());
        memberExtension.setTpsNumber(request.getTpsNumber());
        return memberExtension;
    }
}