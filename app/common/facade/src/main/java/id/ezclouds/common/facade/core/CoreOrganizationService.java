/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.core;

import id.ezclouds.common.model.core.Organization;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrganizationService.java, v 0.1 2024‐09‐23 1:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreOrganizationService {

    List<Organization> getOrganizations();
}