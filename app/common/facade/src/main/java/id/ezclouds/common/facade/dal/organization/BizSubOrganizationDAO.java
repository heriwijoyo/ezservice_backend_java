/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSubOrganizationDAO.java, v 0.1 2024‐07‐28 8:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizSubOrganizationDAO {
    long countByOrgId(String orgId);
}