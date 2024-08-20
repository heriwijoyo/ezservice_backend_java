/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.organization;

import id.ezclouds.common.model.organization.SubOrganization;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SubOrganizationService.java, v 0.1 2024‐08‐11 2:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface SubOrganizationService {
    List<SubOrganization> getSubOrganizationAll(String orgId);
}