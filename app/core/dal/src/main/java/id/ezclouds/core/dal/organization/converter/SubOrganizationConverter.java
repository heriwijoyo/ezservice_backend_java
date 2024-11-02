/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.organization.converter;

import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.core.dal.organization.dataobject.CoreSubOrganizationDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SubOrganizationConverter.java, v 0.1 2024‐11‐02 7:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SubOrganizationConverter extends CommonDOModelConverter<CoreSubOrganizationDO, SubOrganization> {

    @Override
    protected SubOrganization safeConvertQuery(CoreSubOrganizationDO dataObject) {
        SubOrganization subOrganization = new SubOrganization();
        subOrganization.setOrgId(dataObject.getOrgId());
        subOrganization.setSubOrgId(dataObject.getSubOrgId());
        subOrganization.setName(dataObject.getName());
        return subOrganization;
    }

    @Override
    protected CoreSubOrganizationDO safeConvertStore(SubOrganization model) {
        return null;
    }
}