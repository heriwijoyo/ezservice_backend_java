/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthAppClientService;
import id.ezclouds.common.facade.dal.auth.AuthAppClientDAO;
import id.ezclouds.common.model.auth.AuthAppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthAppClientService.java, v 0.1 2024‐08‐12 9:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthAppClientService implements AuthAppClientService {

    @Autowired
    private AuthAppClientDAO authAppClientDAO;

    @Override
    public AuthAppClient getByOrgId(String orgId) {
        return authAppClientDAO.getByOrgId(orgId);
    }
}