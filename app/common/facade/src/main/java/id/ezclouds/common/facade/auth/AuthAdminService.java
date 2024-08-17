/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

import id.ezclouds.common.model.auth.AuthAdminSession;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAdminService.java, v 0.1 2024‐08‐17 7:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthAdminService {
    AuthAdminSession authorizeAdminSession(String sessionId);
}