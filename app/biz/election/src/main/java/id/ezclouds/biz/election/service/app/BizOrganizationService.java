/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.model.core.CoreOrganization;
import id.ezclouds.core.shared.service.EzOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOrganizationService.java, v 0.1 2024‐01‐28 6:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizOrganizationService {

    @Autowired
    private EzOrganizationService ezOrganizationService;

    public List<CoreOrganization> getActiveOrganizations() {
        return ezOrganizationService.getActiveOrganizations();
    }

    public CoreOrganization getOrganizationById(String orgId) {
        if (StringUtil.isBlank(orgId)) {
            return null;
        }

        for (CoreOrganization organization : getActiveOrganizations()) {
            if (orgId.equals(organization.getOrgId())) {
                return organization;
            }
        }
        return null;
    }

    public CoreOrganization getOrganizationByCode(String orgCode) {
        return getActiveOrganizations()
                .stream()
                .filter(org -> StringUtil.equalsNotNull(orgCode, org.getCode()))
                .findFirst()
                .orElse(null);
    }
}