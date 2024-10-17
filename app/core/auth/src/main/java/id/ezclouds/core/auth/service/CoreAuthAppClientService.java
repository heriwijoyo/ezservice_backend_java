/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthAppClientService;
import id.ezclouds.common.facade.dal.auth.AuthAppClientDAO;
import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.common.model.core.CoreCacheKey;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthAppClientService.java, v 0.1 2024‐09‐29 2:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthAppClientService implements AuthAppClientService {

    @Autowired
    private AuthAppClientDAO authAppClientDAO;

    @Override
    @Cacheable(CoreCacheKey.AUTH_APP_CLIENTS)
    public List<AuthAppClient> getAllActiveAppClients() {
        return authAppClientDAO.getAllActive();
    }

    @Override
    public boolean authorizeAppClient(AuthAppClient request) {
        for (AuthAppClient appClient : getAllActiveAppClients()) {
            boolean matchOrgId = StringUtil.equals(appClient.getOrgId(), request.getOrgId());
            boolean matchClientId = StringUtil.equals(appClient.getClientId(), request.getClientId());
            boolean matchAppId = StringUtil.equals(appClient.getAppId(), request.getAppId());
            boolean matchClientSecret = StringUtil.equals(appClient.getClientSecret(), request.getClientSecret());

            if (matchOrgId && matchClientId && matchAppId && matchClientSecret) {
                return true;
            }
        }
        return false;
    }
}