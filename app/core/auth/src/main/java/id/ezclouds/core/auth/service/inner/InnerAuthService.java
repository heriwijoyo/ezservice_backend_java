/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service.inner;

import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.repo.EzAuthAdminCommonSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: InnerAuthService.java, v 0.1 2024‐02‐10 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class InnerAuthService {

    @Autowired
    private EzAuthAdminCommonSessionRepository ezAuthAdminCommonSessionRepository;

    @Transactional
    public void adminCreateSession(EzAuthAdminCommonSessionDO sessionDO) {
        ezAuthAdminCommonSessionRepository.saveAndFlush(sessionDO);
    }
}