/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.converter;

import id.ezclouds.common.model.member.BizMemberImport;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.member.dataobject.CoreMemberImportDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberImportStoreConverter.java, v 0.1 2024‐08‐11 6:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberImportStoreConverter extends TemplateModelConverter<BizMemberImport, CoreMemberImportDO> {

    @Override
    protected CoreMemberImportDO safeConvert(BizMemberImport input) {
        CoreMemberImportDO memberImportDO = new CoreMemberImportDO();
        memberImportDO.setBizMemberId(input.getBizMemberId());
        memberImportDO.setOrgId(input.getOrgId());
        memberImportDO.setSubOrgId(input.getSubOrgId());
        memberImportDO.setSourceId(input.getSourceId());
        memberImportDO.setCreatedTime(input.getCreatedTime());
        memberImportDO.setName(input.getName());
        memberImportDO.setGender(input.getGender());
        memberImportDO.setAgeGroup(input.getAgeGroup());
        memberImportDO.setPhone(input.getPhone());
        memberImportDO.setEducation(input.getEducation());
        memberImportDO.setOccupation(input.getOccupation());
        memberImportDO.setReligion(input.getReligion());
        memberImportDO.setEthnic(input.getEthnic());
        memberImportDO.setIdCardNumber(input.getIdCardNumber());
        memberImportDO.setDistrictName(input.getDistrictName());
        memberImportDO.setVillageName(input.getVillageName());
        memberImportDO.setRukunWarga(input.getRukunWarga());
        memberImportDO.setRukunTetangga(input.getRukunTetangga());
        memberImportDO.setTpsNumber(input.getTpsNumber());
        memberImportDO.setAddress(input.getAddress());
        return memberImportDO;
    }
}