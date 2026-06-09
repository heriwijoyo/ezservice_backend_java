/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth;

import id.ezclouds.common.facade.dal.auth.AuthWebPublicSessionDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.auth.WebPublicSession;
import id.ezclouds.core.dal.auth.converter.WebPublicSessionConverter;
import id.ezclouds.core.dal.auth.repo.WebPublicSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthWebPublicSessionDAO.java, v 0.1 2024‐10‐10 1:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAuthWebPublicSessionDAO implements AuthWebPublicSessionDAO {

    @Autowired
    private WebPublicSessionRepository webPublicSessionRepository;

    @Override
    @EzDAOLogger
    public void store(WebPublicSession webPublicSession) {
        webPublicSessionRepository
                .saveAndFlush(
                        new WebPublicSessionConverter()
                                .convertStore(webPublicSession)
                );
    }
}