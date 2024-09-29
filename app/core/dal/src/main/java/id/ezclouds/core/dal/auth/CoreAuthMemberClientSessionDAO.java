/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth;

import id.ezclouds.common.facade.dal.auth.AuthMemberClientSessionDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.auth.AuthMemberSession;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthMemberClientSessionDAO.java, v 0.1 2024‐09‐30 1:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAuthMemberClientSessionDAO implements AuthMemberClientSessionDAO {

    @Override
    @EzDAOLogger
    public AuthMemberSession authMemberSessionId(String sessionId) {
        return null;
    }
}