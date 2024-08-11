/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.converter;

import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.member.dataobject.CoreMemberBackOfficeDO;
import id.ezclouds.core.dal.member.dataobject.CoreMemberExtBackOfficeDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberBackOfficeResultConverter.java, v 0.1 2024‐08‐11 6:45 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberBackOfficeResultConverter extends TemplateModelConverter<CoreMemberBackOfficeDO, MemberBackOffice> {

    private Map<String, CoreMemberExtBackOfficeDO> memberExtensionMap = new HashMap<>();

    public MemberBackOfficeResultConverter(List<CoreMemberExtBackOfficeDO> memberExtensions) {
        if (memberExtensions != null) {
            for (CoreMemberExtBackOfficeDO extension : memberExtensions) {
                memberExtensionMap.put(extension.getMemberId(), extension);
            }
        }
    }

    @Override
    protected MemberBackOffice safeConvert(CoreMemberBackOfficeDO input) {
        MemberBackOffice memberBackOffice = new MemberBackOffice();
        memberBackOffice.setMemberId(input.getMemberId());
        memberBackOffice.setMemberName(input.getName());
        memberBackOffice.setPhone(input.getPhone());
        memberBackOffice.setSubOrgId(input.getSubOrgId());

        CoreMemberExtBackOfficeDO extBackOfficeDO = memberExtensionMap.get(input.getMemberId());
        if (extBackOfficeDO != null) {
            memberBackOffice.setIdCardNumber(extBackOfficeDO.getIdCardNumber());
            memberBackOffice.setProvinceName(extBackOfficeDO.getProvinceName());
            memberBackOffice.setRegencyName(extBackOfficeDO.getRegencyName());
            memberBackOffice.setDistrictName(extBackOfficeDO.getDistrictName());
            memberBackOffice.setVillageName(extBackOfficeDO.getVillageName());
        }
        return memberBackOffice;
    }
}