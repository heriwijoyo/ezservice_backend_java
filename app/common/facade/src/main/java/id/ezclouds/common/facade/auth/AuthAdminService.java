/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.util.exception.EzErrorException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAdminService.java, v 0.1 2024‐08‐17 7:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthAdminService {

    AuthAdminSession authenticateAdminSession(String sessionId) throws EzErrorException;

    void authorizeSessionForRole(AuthAdminSession session, AuthRole role) throws EzErrorException;

    void authorizeWebPublicSession(String sessionId) throws EzErrorException;
}