/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.facade.dal.config.CoreConfigDAO;
import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.config.CoreConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConfigService.java, v 0.1 2024‐09‐01 4:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreConfigService implements CoreConfigService {

    @Autowired
    private CoreConfigDAO coreConfigDAO;

    @Override
    public CoreConfig getOrgConfig(String orgId, CoreConfigType configType) {
        return coreConfigDAO.getConfig(orgId, configType);
    }

    @Override
    public CoreConfig getOrgConfig(CoreConfigType configType) {
        return coreConfigDAO.getConfig(configType);
    }

    @Override
    public Map<CoreConfigType, CoreConfig> getOrgConfigMap(String orgId, CoreConfigType... configTypes) {
        Map<CoreConfigType, CoreConfig> configMap = new HashMap<>();
        for (CoreConfigType configType : configTypes) {
            configMap.put(configType, coreConfigDAO.getConfig(orgId, configType));
        }
        return configMap;
    }

    @Override
    public void store(CoreConfig coreConfig) {
        coreConfigDAO.store(coreConfig);
    }
}