/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.core.dal.member.dataobject.CoreMemberBackOfficeDO;
import id.ezclouds.core.dal.member.dataobject.CoreMemberExtBackOfficeDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberBackOfficeConverter.java, v 0.1 2024‐08‐30 12:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberBackOfficeConverter extends CommonDOModelConverter<CoreMemberBackOfficeDO, MemberBackOffice> {

    private Map<String, CoreMemberExtBackOfficeDO> memberExtensionMap = new HashMap<>();

    public CoreMemberBackOfficeConverter() {
    }

    public CoreMemberBackOfficeConverter(List<CoreMemberExtBackOfficeDO> memberExtensions) {
        if (memberExtensions != null) {
            for (CoreMemberExtBackOfficeDO extension : memberExtensions) {
                memberExtensionMap.put(extension.getMemberId(), extension);
            }
        }
    }

    public CoreMemberBackOfficeConverter(CoreMemberExtBackOfficeDO memberExtension) {
        if (memberExtension != null) {
            memberExtensionMap.put(memberExtension.getMemberId(), memberExtension);
        }
    }

    @Override
    protected MemberBackOffice safeConvertQuery(CoreMemberBackOfficeDO model) {
        MemberBackOffice memberBackOffice = new MemberBackOffice();
        memberBackOffice.setMemberId(model.getMemberId());
        memberBackOffice.setOrgId(model.getOrgId());
        memberBackOffice.setRoles(model.getRoles());
        memberBackOffice.setName(model.getName());
        memberBackOffice.setGender(model.getGender());
        memberBackOffice.setDateOfBirth(model.getDateOfBirth());
        memberBackOffice.setPhone(model.getPhone());
        memberBackOffice.setEducation(model.getEducation());
        memberBackOffice.setOccupation(model.getOccupation());
        memberBackOffice.setReligion(model.getReligion());
        memberBackOffice.setEthnic(model.getEthnic());
        memberBackOffice.setSubOrgId(model.getSubOrgId());

        CoreMemberExtBackOfficeDO extBackOfficeDO = memberExtensionMap.get(model.getMemberId());
        if (extBackOfficeDO != null) {
            memberBackOffice.setIdCardNumber(extBackOfficeDO.getIdCardNumber());
            memberBackOffice.setProvinceName(extBackOfficeDO.getProvinceName());
            memberBackOffice.setRegencyName(extBackOfficeDO.getRegencyName());
            memberBackOffice.setDistrictName(extBackOfficeDO.getDistrictName());
            memberBackOffice.setVillageName(extBackOfficeDO.getVillageName());
            memberBackOffice.setRukunWarga(extBackOfficeDO.getRukunWarga());
            memberBackOffice.setRukunTetangga(extBackOfficeDO.getRukunTetangga());
            memberBackOffice.setTpsNumber(extBackOfficeDO.getTpsNumber());
        }
        return memberBackOffice;
    }

    @Override
    protected CoreMemberBackOfficeDO safeConvertStore(MemberBackOffice memberBackOffice) {
        CoreMemberBackOfficeDO memberBackOfficeDO = new CoreMemberBackOfficeDO();
        memberBackOfficeDO.setMemberId(memberBackOffice.getMemberId());
        memberBackOfficeDO.setOrgId(memberBackOffice.getOrgId());
        memberBackOfficeDO.setRoles(memberBackOffice.getRoles());
        memberBackOfficeDO.setName(memberBackOffice.getName());
        memberBackOfficeDO.setGender(memberBackOffice.getGender());
        memberBackOfficeDO.setDateOfBirth(memberBackOffice.getDateOfBirth());
        memberBackOfficeDO.setPhone(memberBackOffice.getPhone());
        memberBackOfficeDO.setEducation(memberBackOffice.getEducation());
        memberBackOfficeDO.setOccupation(memberBackOffice.getOccupation());
        memberBackOfficeDO.setReligion(memberBackOffice.getReligion());
        memberBackOfficeDO.setEthnic(memberBackOffice.getEthnic());
        memberBackOfficeDO.setSubOrgId(memberBackOffice.getSubOrgId());
        return memberBackOfficeDO;
    }
}