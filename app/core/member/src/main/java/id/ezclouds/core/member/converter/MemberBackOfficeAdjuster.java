/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.member.converter;

import id.ezclouds.common.model.member.MemberBackOffice;
import id.ezclouds.common.model.organization.SubOrganization;
import id.ezclouds.common.model.util.ModelAdjuster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberBackOfficeAdjuster.java, v 0.1 2024‐08‐11 9:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberBackOfficeAdjuster implements ModelAdjuster<MemberBackOffice> {

    private Map<String, SubOrganization> subOrganizationMap = new HashMap<>();

    public MemberBackOfficeAdjuster(List<SubOrganization> subOrganizations) {
        if (subOrganizations != null) {
            for (SubOrganization subOrganization : subOrganizations) {
                subOrganizationMap.put(subOrganization.getSubOrgId(), subOrganization);
            }
        }
    }

    @Override
    public void adjust(MemberBackOffice origin) {
        SubOrganization subOrganization = subOrganizationMap.get(origin.getSubOrgId());
        if (subOrganization != null) {
            origin.setSubOrgName(subOrganization.getName());
        }
    }
}