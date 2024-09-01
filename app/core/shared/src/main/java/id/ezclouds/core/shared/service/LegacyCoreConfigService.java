/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

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
import java.util.Arrays;
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

    private static final List<String> CORE_ORG_CONFIG_KEYS;

    static {
        CORE_ORG_CONFIG_KEYS = Arrays.asList(
                CoreConstant.ConfigKey.MEMBER_CLIENT_ALLOW_MULTIPLE_SESSION,
                CoreConstant.ConfigKey.MEMBER_CLIENT_SESSION_EXPIRY_DAYS,
                CoreConstant.ConfigKey.MEMBER_COMMON_SESSION_EXPIRY_MINS,
                CoreConstant.ConfigKey.ADMIN_COMMON_SESSION_EXPIRY_MINS,
                CoreConstant.ConfigKey.WATZAP_SEND_ENABLE,
                CoreConstant.ConfigKey.WATZAP_API_KEY,
                CoreConstant.ConfigKey.WATZAP_NUMBER_KEY
        );
    }

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

    public boolean isWatzapSendEnable(String orgId) {
        String configValue = getOrgConfigValue(CoreConstant.ConfigKey.WATZAP_SEND_ENABLE, orgId);
        return Boolean.parseBoolean(configValue);
    }

    public String getWatzapApiKey(String orgId) {
        return getOrgConfigValue(CoreConstant.ConfigKey.WATZAP_API_KEY, orgId);
    }

    public String getWatzapNumberKey(String orgId) {
        return getOrgConfigValue(CoreConstant.ConfigKey.WATZAP_NUMBER_KEY, orgId);
    }

    public String getWatzapApiUri() {
        return getConfigValue(CoreConstant.ConfigKey.WATZAP_API_URI);
    }

    public String getCoreAreaLevelRoot(String orgId) {
        return getOrgConfigValue(CoreConstant.ConfigKey.CORE_AREA_LEVEL_ROOT, orgId);
    }

    public List<String> getCoreAreaRootIds(String orgId) {
        String configValue = getOrgConfigValue(CoreConstant.ConfigKey.CORE_AREA_ROOT_IDS, orgId);
        return Arrays.asList(configValue.split(","));
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

        for (String configKey : CORE_ORG_CONFIG_KEYS) {
            String configValue = StringUtil.EMPTY;
            for (CoreOrgConfig orgConfig : coreOrgConfigs) {
                if (configKey.equals(orgConfig.getConfigKey())) {
                    configValue = orgConfig.getConfigValue();
                }
            }
            orgConfigMap.put(configKey, configValue);
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