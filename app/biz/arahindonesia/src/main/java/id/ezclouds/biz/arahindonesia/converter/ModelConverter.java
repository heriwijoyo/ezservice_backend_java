/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.common.dal.model.OrganizationDO;
import id.ezclouds.core.shared.model.Organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ModelConverter.java, v 0.1 2023‐12‐10 12:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class ModelConverter {

    public static Organization convert(OrganizationDO organizationDO) {
        return new Organization(organizationDO.getOrgId(), organizationDO.getName());
    }
}