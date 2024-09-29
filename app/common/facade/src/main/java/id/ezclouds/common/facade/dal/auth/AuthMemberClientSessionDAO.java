/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.auth;

import id.ezclouds.common.model.auth.AuthMemberSession;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthMemberClientSessionDAO.java, v 0.1 2024‐08‐14 8:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthMemberClientSessionDAO {

    AuthMemberSession authMemberSessionId(String sessionId);
}