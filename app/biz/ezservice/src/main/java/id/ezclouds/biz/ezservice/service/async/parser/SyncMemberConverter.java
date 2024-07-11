/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.parser;

import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.common.util.DateUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SyncMemberConverter.java, v 0.1 2024‐07‐11 4:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SyncMemberConverter implements ImportConverter<BizMember, BizMemberImportDO> {

    private final String orgId;
    private final String sourceId;

    public SyncMemberConverter(String orgId, String sourceId) {
        this.orgId = orgId;
        this.sourceId = sourceId;
    }

    @Override
    public BizMemberImportDO convert(BizMember input) {
        BizMemberImportDO memberImportDO = new BizMemberImportDO();
        memberImportDO.setBizMemberId(input.getMemberId());
        memberImportDO.setOrgId(orgId);
        memberImportDO.setSourceId(sourceId);
        memberImportDO.setName(input.getName());
        memberImportDO.setGender(input.getGender().getCode());
        memberImportDO.setDateOfBirth(input.getDateOfBirth());
        memberImportDO.setPhone(input.getPhone());
        memberImportDO.setEducation(input.getEducation());
        memberImportDO.setOccupation(input.getOccupation());
        memberImportDO.setReligion(input.getReligion());
        memberImportDO.setEthnic(input.getEthnic());
        memberImportDO.setDistrictName(input.getDistrictName());
        memberImportDO.setVillageName(input.getVillageName());
        memberImportDO.setRukunWarga(input.getRukunWarga());
        memberImportDO.setRukunTetangga(input.getRukunTetangga());
        memberImportDO.setTpsNumber(input.getTpsNumber());
        memberImportDO.setCreatedTime(DateUtil.getCurrentFormattedDate());

        if (input.getSubOrganization() != null) {
            memberImportDO.setSubOrgId(input.getSubOrganization().getSubOrgId());
        }
        return memberImportDO;
    }
}