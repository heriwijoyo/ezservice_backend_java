/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.organization;

import id.ezclouds.common.model.organization.SubOrganization;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SubOrganizationDAO.java, v 0.1 2024‐07‐28 8:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface SubOrganizationDAO {
    long countByOrgId(String orgId);
    List<SubOrganization> getByOrgId(String orgId);
    SubOrganization getById(String subOrgId);
}