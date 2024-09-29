/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

import id.ezclouds.common.model.auth.AuthMemberClient;
import id.ezclouds.common.model.auth.AuthRole;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthBizMemberService.java, v 0.1 2024‐08‐13 5:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AuthBizMemberService {

    AuthMemberClient getMemberClientOrCreateIfNotExist(String orgId, String memberId, String loginType, String loginId);

    String resetLoginPassword(String clientId);

    void authMemberAppSession(String sessionId, AuthRole authRole);

    void invalidateMemberSession(String orgId, String client);
}