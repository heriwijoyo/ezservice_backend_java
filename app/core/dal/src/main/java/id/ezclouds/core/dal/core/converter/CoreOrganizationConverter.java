/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.organization.Organization;
import id.ezclouds.core.dal.core.dataobject.CoreOrganizationDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrganizationConverter.java, v 0.1 2024‐09‐23 1:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreOrganizationConverter extends CommonDOModelConverter<CoreOrganizationDO, Organization> {

    @Override
    protected Organization safeConvertQuery(CoreOrganizationDO dataObject) {
        return new Organization(
                dataObject.getOrgId(),
                dataObject.getCode(),
                dataObject.getName()
        );
    }

    @Override
    protected CoreOrganizationDO safeConvertStore(Organization model) {
        CoreOrganizationDO organizationDO = new CoreOrganizationDO();
        organizationDO.setOrgId(model.getOrgId());
        organizationDO.setCode(model.getOrgCode());
        organizationDO.setName(model.getOrgName());
        return organizationDO;
    }
}