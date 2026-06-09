/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.organization.converter;

import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.organization.dataobject.CoreSubOrganizationDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSubOrganizationResultConverter.java, v 0.1 2024‐08‐11 9:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreSubOrganizationResultConverter extends TemplateModelConverter<CoreSubOrganizationDO, SubOrganization> {

    @Override
    protected SubOrganization safeConvert(CoreSubOrganizationDO input) {
        SubOrganization subOrganization = new SubOrganization();
        subOrganization.setSubOrgId(input.getSubOrgId());
        subOrganization.setOrgId(input.getOrgId());
        subOrganization.setName(input.getName());
        subOrganization.setAddress(input.getAddress());
        return subOrganization;
    }
}