/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthSession.java, v 0.1 2024‐09‐09 9:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthSession {

    String getSessionId();
    AuthScene getAuthScene();
    String getOrgId();
    String getOrgCode();
    List<AuthRole> getAuthRoles();

}