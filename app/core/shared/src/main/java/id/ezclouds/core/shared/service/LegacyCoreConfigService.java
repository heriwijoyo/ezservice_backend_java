/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.constant.CoreConstant;
import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.model.CoreConfig;
import id.ezclouds.core.shared.model.CoreOrgConfig;
import id.ezclouds.core.shared.repo.CoreConfigRepository;
import id.ezclouds.core.shared.repo.CoreOrgConfigRepository;
import id.ezclouds.core.shared.repo.dataobject.EzCoreOrgConfigDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: LegacyCoreConfigService.java, v 0.1 2024‐02‐04 3:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class LegacyCoreConfigService {

    @Autowired
    private CoreConfigRepository coreConfigRepository;

    @Autowired
    private CoreOrgConfigRepository coreOrgConfigRepository;

    public String getOrgConfigValue(String configKey, String orgId) {
        return getCoreOrgConfigs()
                .stream()
                .filter(config -> StringUtil.equalsNotNull(configKey, config.getConfigKey()) &&
                        StringUtil.equalsNotNull(orgId, config.getOrgId())
                )
                .findFirst()
                .orElse(new CoreOrgConfig())
                .getConfigValue();
    }

    public String getConfigValue(String configKey) {
        return getCoreConfigs()
                .stream()
                .filter(config -> StringUtil.equalsNotNull(configKey, config.getConfigKey()))
                .findFirst()
                .orElse(new CoreConfig())
                .getConfigValue();
    }

    @Cacheable(CoreConstant.CacheKey.CORE_CONFIG)
    public List<CoreConfig> getCoreConfigs() {
        return coreConfigRepository.findAll()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    @Cacheable(CoreConstant.CacheKey.CORE_ORG_CONFIG)
    public List<CoreOrgConfig> getCoreOrgConfigs() {
        return coreOrgConfigRepository.findAll()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public Map<String, String> getOrgConfigByOrgId(String orgId) {
        Map<String, String> orgConfigMap = new HashMap<>();
        List<CoreOrgConfig> coreOrgConfigs = coreOrgConfigRepository
                .findByOrgId(orgId)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());

        for (CoreOrgConfigType configType: CoreOrgConfigType.values()) {
            String configValue = StringUtil.EMPTY;
            for (CoreOrgConfig orgConfig : coreOrgConfigs) {
                if (configType.getCode().equals(orgConfig.getConfigKey())) {
                    configValue = orgConfig.getConfigValue();
                }
            }
            orgConfigMap.put(configType.getCode(), configValue);
        }

        return orgConfigMap;
    }

    @Transactional
    public void saveCoreOrgConfig(String orgId, String configKey, String configValue) {
        EzCoreOrgConfigDO configDO = coreOrgConfigRepository
                .findByOrgIdAndConfigKey(orgId, configKey);
        if (configDO == null) {
            configDO = new EzCoreOrgConfigDO();
            configDO.setConfigId(HashUtil.createHash(orgId, configKey));
            configDO.setOrgId(orgId);
            configDO.setConfigKey(configKey);
        }

        configDO.setConfigValue(configValue);
        coreOrgConfigRepository.saveAndFlush(configDO);
    }
}