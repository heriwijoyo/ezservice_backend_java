/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth;

import id.ezclouds.common.facade.dal.auth.AuthMemberClientSessionDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.auth.AuthMemberClientSession;
import id.ezclouds.core.dal.auth.converter.AuthMemberClientSessionConverter;
import id.ezclouds.core.dal.auth.repo.AuthMemberClientSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthMemberClientSessionDAO.java, v 0.1 2024‐09‐30 1:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAuthMemberClientSessionDAO implements AuthMemberClientSessionDAO {

    @Autowired
    private AuthMemberClientSessionRepository authMemberClientSessionRepository;

    @Override
    @EzDAOLogger
    public AuthMemberClientSession findSessionById(String sessionId) {
        return new AuthMemberClientSessionConverter()
                .convertQuery(
                        authMemberClientSessionRepository
                                .findById(sessionId)
                                .orElse(null)
                );
    }

    @Override
    @EzDAOLogger
    public void store(AuthMemberClientSession session) {
        authMemberClientSessionRepository
                .saveAndFlush(
                        new AuthMemberClientSessionConverter()
                                .convertStore(session)
                );
    }
}