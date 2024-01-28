/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.converter;

import id.ezclouds.core.shared.model.CoreOrganization;
import id.ezclouds.core.shared.repo.dataobject.EzCoreOrganizationDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreModelConverter.java, v 0.1 2024‐01‐28 6:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreModelConverter {

    public static CoreOrganization convert(EzCoreOrganizationDO organizationDO) {
        if (organizationDO == null) { return null; }
        CoreOrganization coreOrganization = new CoreOrganization();
        coreOrganization.setOrgId(organizationDO.getOrgId());
        coreOrganization.setName(organizationDO.getName());
        coreOrganization.setCode(organizationDO.getCode());
        return coreOrganization;
    }
}