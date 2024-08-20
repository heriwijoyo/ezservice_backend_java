/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.auth;

import id.ezclouds.common.model.auth.AuthAppClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAppClientDAO.java, v 0.1 2024‐08‐13 5:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthAppClientDAO {
    AuthAppClient getByOrgId(String orgId);
}