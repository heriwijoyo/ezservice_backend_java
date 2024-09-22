/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.core;

import id.ezclouds.common.model.core.Organization;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrganizationDAO.java, v 0.1 2024‐09‐23 1:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreOrganizationDAO {

    List<Organization> getOrganizations();
}