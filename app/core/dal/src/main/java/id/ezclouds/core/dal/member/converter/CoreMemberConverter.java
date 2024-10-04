/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.CoreStatus;
import id.ezclouds.common.model.core.member.CoreGender;
import id.ezclouds.common.model.core.member.CoreMember;
import id.ezclouds.core.dal.member.dataobject.CoreMemberDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberConverter.java, v 0.1 2024‐10‐05 3:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberConverter extends CommonDOModelConverter<CoreMemberDO, CoreMember> {

    @Override
    protected CoreMember safeConvertQuery(CoreMemberDO dataObject) {
        CoreMember coreMember = new CoreMember();
        coreMember.setMemberId(dataObject.getMemberId());
        coreMember.setOrgId(dataObject.getOrgId());
        coreMember.setSubOrgId(dataObject.getSubOrgId());
        coreMember.setShard(dataObject.getShard());
        coreMember.setSourceId(dataObject.getSourceId());
        coreMember.setReferrerId(dataObject.getReferrerId());
        coreMember.setRoles(dataObject.getRoles());
        coreMember.setName(dataObject.getName());
        coreMember.setNickname(dataObject.getNickname());
        coreMember.setGender(CoreGender.getByCode(dataObject.getGender()));
        coreMember.setDateOfBirth(dataObject.getDateOfBirth());
        coreMember.setPhone(dataObject.getPhone());
        coreMember.setEducation(dataObject.getEducation());
        coreMember.setOccupation(dataObject.getOccupation());
        coreMember.setReligion(dataObject.getReligion());
        coreMember.setEthnic(dataObject.getEthnic());
        coreMember.setEmail(dataObject.getEmail());
        coreMember.setAddress(dataObject.getAddress());
        coreMember.setAvatarUrl(dataObject.getAvatarUrl());
        coreMember.setPhoneVerified(dataObject.isPhoneVerified());
        coreMember.setEmailVerified(dataObject.isEmailVerified());
        coreMember.setAddressVerified(dataObject.isAddressVerified());
        coreMember.setCreatedTime(dataObject.getCreatedTime());
        coreMember.setModifiedTime(dataObject.getModifiedTime());
        coreMember.setStatus(CoreStatus.getByStatus(dataObject.getStatus()));
        return coreMember;
    }

    @Override
    protected CoreMemberDO safeConvertStore(CoreMember model) {
        CoreMemberDO memberDO = new CoreMemberDO();
        memberDO.setMemberId(model.getMemberId());
        memberDO.setOrgId(model.getOrgId());
        memberDO.setSubOrgId(model.getSubOrgId());
        memberDO.setShard(model.getShard());
        memberDO.setSourceId(model.getSourceId());
        memberDO.setReferrerId(model.getReferrerId());
        memberDO.setRoles(model.getRoles());
        memberDO.setName(model.getName());
        memberDO.setNickname(model.getNickname());
        memberDO.setGender(model.getGender().getCode());
        memberDO.setDateOfBirth(model.getDateOfBirth());
        memberDO.setPhone(model.getPhone());
        memberDO.setEducation(model.getEducation());
        memberDO.setOccupation(model.getOccupation());
        memberDO.setReligion(model.getReligion());
        memberDO.setEthnic(model.getEthnic());
        memberDO.setEmail(model.getEmail());
        memberDO.setAddress(model.getAddress());
        memberDO.setAvatarUrl(model.getAvatarUrl());
        memberDO.setPhoneVerified(model.isPhoneVerified());
        memberDO.setEmailVerified(model.isEmailVerified());
        memberDO.setAddressVerified(model.isAddressVerified());
        memberDO.setCreatedTime(model.getCreatedTime());
        memberDO.setModifiedTime(model.getModifiedTime());
        memberDO.setStatus(model.getStatus().getCode());
        return memberDO;
    }
}