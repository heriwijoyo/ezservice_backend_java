/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.facade.dal.config.CoreConfigDAO;
import id.ezclouds.common.model.config.CoreCommonConfigType;
import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.config.CoreConfigType;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.model.core.CoreCacheKey;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
    @Cacheable(CoreCacheKey.CORE_CONFIGS)
    public List<CoreConfig> getAllConfigs() {
        return coreConfigDAO.getAllActive();
    }

    @Override
    public CoreConfig getOrgConfig(String orgId, CoreConfigType configType) {
        return getOrgCoreConfig(orgId, configType);
    }

    @Override
    public Map<CoreConfigType, CoreConfig> getOrgConfigMap(String orgId, CoreConfigType... configTypes) {
        Map<CoreConfigType, CoreConfig> configMap = new HashMap<>();
        for (CoreConfigType configType : configTypes) {
            configMap.put(configType, getOrgCoreConfig(orgId, configType));
        }
        return configMap;
    }

    @Override
    public Map<CoreConfigType, CoreConfig> getOrgConfigMap(CoreConfigType... configTypes) {
        Map<CoreConfigType, CoreConfig> configMap = new HashMap<>();
        for (CoreConfigType configType : configTypes) {
            configMap.put(configType, getCoreConfig(configType));
        }
        return configMap;
    }

    @Override
    public Map<String, String> getOrgRawConfigMap(String orgId) {
        List<CoreConfig> allConfigs = coreConfigDAO.getAllConfig(orgId);

        Map<String, String> rawConfigMap = new LinkedHashMap<>();
        for (CoreOrgConfigType configType : CoreOrgConfigType.values()) {
            rawConfigMap.put(configType.getCode(), getOrgConfigValue(allConfigs, configType));
        }
        return rawConfigMap;
    }

    @Override
    @Transactional
    public void store(CoreConfig reqConfig) {
        CoreConfig dbCoreConfig = getExistConfig(reqConfig);
        if (dbCoreConfig == null) {
            dbCoreConfig = createCopyInstance(reqConfig);
        }
        dbCoreConfig.setConfigValue(reqConfig.getConfigValue());

        coreConfigDAO.store(dbCoreConfig);
    }

    private CoreConfig getOrgCoreConfig(String orgId, CoreConfigType configType) {
        for (CoreConfig coreConfig : getAllConfigs()) {
            if (StringUtil.equals(coreConfig.getOrgId(), orgId) && StringUtil.equals(coreConfig.getConfigKey(), configType.getCode())) {
                return coreConfig;
            }
        }
        return null;
    }

    private CoreConfig getCoreConfig(CoreConfigType configType) {
        for (CoreConfig coreConfig : getAllConfigs()) {
            if (StringUtil.equals(coreConfig.getConfigKey(), configType.getCode())) {
                return coreConfig;
            }
        }
        return null;
    }

    private String getOrgConfigValue(List<CoreConfig> configs, CoreConfigType configType) {
        for (CoreConfig coreConfig : configs) {
            if (configType.getCode().equals(coreConfig.getConfigKey())) {
                return coreConfig.getConfigValue() != null ? coreConfig.getConfigValue() : StringUtil.EMPTY;
            }
        }
        return StringUtil.EMPTY;
    }

    private CoreConfig getExistConfig(CoreConfig reqConfig) {
        if (StringUtil.isNotBlank(reqConfig.getOrgId())) {
            return coreConfigDAO.getConfig(reqConfig.getOrgId(), CoreOrgConfigType.getByCode(reqConfig.getConfigKey()));
        } else {
            return coreConfigDAO.getConfig(CoreCommonConfigType.getByCode(reqConfig.getConfigKey()));
        }
    }

    private CoreConfig createCopyInstance(CoreConfig reqConfig) {
        CoreConfig coreConfig = new CoreConfig();
        coreConfig.setConfigKey(reqConfig.getConfigKey());

        if (StringUtil.isNotBlank(reqConfig.getOrgId())) {
            coreConfig.setOrgId(reqConfig.getOrgId());
            coreConfig.setConfigId(HashUtil.createHash(reqConfig.getOrgId(), reqConfig.getConfigKey()));
        } else {
            coreConfig.setConfigId(HashUtil.createHash(reqConfig.getConfigKey()));
        }
        return coreConfig;
    }
}