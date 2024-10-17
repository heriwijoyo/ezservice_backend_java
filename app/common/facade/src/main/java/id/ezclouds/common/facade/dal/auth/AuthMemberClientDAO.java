/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.auth;

import id.ezclouds.common.model.auth.AuthMemberClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthMemberClientDAO.java, v 0.1 2024‐08‐13 11:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthMemberClientDAO {

    AuthMemberClient getMemberClient(String clientId);

    AuthMemberClient getMemberClient(String orgId, String appId, String loginType, String loginId);

    void store(AuthMemberClient memberClient);

    void updateLoginPassword(String clientId, String password);
}