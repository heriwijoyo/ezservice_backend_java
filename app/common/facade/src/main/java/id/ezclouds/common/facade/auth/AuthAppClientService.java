/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

import id.ezclouds.common.model.auth.AuthAppClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAppClientService.java, v 0.1 2024‐08‐12 9:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthAppClientService {
    AuthAppClient getByOrgId(String orgId);
}