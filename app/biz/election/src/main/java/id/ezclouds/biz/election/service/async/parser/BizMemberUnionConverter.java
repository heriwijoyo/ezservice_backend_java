/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.async.parser;

import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.biz.election.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.election.service.core.dataobject.BizMemberUnionDO;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;

import java.util.Arrays;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUnionConverter.java, v 0.1 2024‐07‐15 3:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberUnionConverter {

    private static String[] orgAdminIds = {"2000210000000000", "2000110000000000", "2000710000000023"};

    public static BizMemberUnionDO convert(BizMember bizMember) {
        if (bizMember == null) { return null; }
        if (Arrays.asList(orgAdminIds).contains(bizMember.getMemberId())) {
            return null;
        }
        BizMemberUnionDO unionDO = new BizMemberUnionDO();
        unionDO.setBizUnionId(HashUtil.createHash("APP", bizMember.getMemberId()));
        unionDO.setSource("APP");
        unionDO.setSourceId(bizMember.getMemberId());
        unionDO.setSubOrgId(bizMember.getSubOrganization().getSubOrgId());
        unionDO.setRole(getRole(bizMember.getReferrerId()));
        unionDO.setName(StringUtil.toTitleCase(bizMember.getName()));
        unionDO.setGender(bizMember.getGender().getCode());
        unionDO.setDateOfBirth(bizMember.getDateOfBirth());
        unionDO.setPhone(bizMember.getPhone());
        unionDO.setEducation(bizMember.getEducation());
        unionDO.setOccupation(bizMember.getOccupation());
        unionDO.setReligion(bizMember.getReligion());
        unionDO.setEthnic(bizMember.getEthnic());
        unionDO.setIdCardNumber(bizMember.getIdCardNumber());
        unionDO.setDistrictId(bizMember.getDistrictId());
        unionDO.setDistrictName(bizMember.getDistrictName());
        unionDO.setVillageId(bizMember.getVillageId());
        unionDO.setVillageName(bizMember.getVillageName());
        unionDO.setRukunWarga(bizMember.getRukunWarga());
        unionDO.setRukunTetangga(bizMember.getRukunTetangga());
        unionDO.setTpsNumber(bizMember.getTpsNumber());
        unionDO.setCreatedDate(DateUtil.getFormattedDateFromDateTime(bizMember.getCreatedTime()));
        unionDO.setCreatedTime(bizMember.getCreatedTime());

        return unionDO;
    }

    public static BizMemberUnionDO convertMemberImport(BizMemberImportDO bizMember) {
        if (bizMember == null) { return null; }
        BizMemberUnionDO unionDO = new BizMemberUnionDO();
        unionDO.setBizUnionId(HashUtil.createHash("IMPORT", bizMember.getBizMemberId()));
        unionDO.setSource("IMPORT");
        unionDO.setSourceId(bizMember.getBizMemberId());
        unionDO.setSubOrgId(bizMember.getSubOrgId());
        unionDO.setName(bizMember.getName());
        unionDO.setGender(bizMember.getGender());
        unionDO.setDateOfBirth(bizMember.getDateOfBirth());
        unionDO.setPhone(bizMember.getPhone());
        unionDO.setEducation(bizMember.getEducation());
        unionDO.setOccupation(bizMember.getOccupation());
        unionDO.setReligion(bizMember.getReligion());
        unionDO.setEthnic(bizMember.getEthnic());
        unionDO.setIdCardNumber(bizMember.getIdCardNumber());
        unionDO.setDistrictName(bizMember.getDistrictName());
        unionDO.setVillageName(bizMember.getVillageName());
        unionDO.setRukunWarga(bizMember.getRukunWarga());
        unionDO.setRukunTetangga(bizMember.getRukunTetangga());
        unionDO.setTpsNumber(bizMember.getTpsNumber());
        unionDO.setCreatedDate(DateUtil.getFormattedDateFromDateTime(bizMember.getCreatedTime()));
        unionDO.setCreatedTime(bizMember.getCreatedTime());

        return unionDO;
    }

    private static String getRole(String referrerId) {
        for (String orgAdminId : orgAdminIds) {
            if (orgAdminId.equals(referrerId)) {
                return "S";
            }
        }
        return "L";
    }
}