/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.constant.CoreConstant;
import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.model.CoreConfig;
import id.ezclouds.core.shared.repo.CoreConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConfigService.java, v 0.1 2024‐02‐04 3:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreConfigService {

    @Autowired
    private CoreConfigRepository coreConfigRepository;

    public String getConfigValue(String configKey, String orgId) {
        return getCoreConfigs()
                .stream()
                .filter(config -> StringUtil.equalsNotNull(configKey, config.getConfigKey()) &&
                        StringUtil.equalsNotNull(orgId, config.getOrgId())
                )
                .findFirst()
                .orElse(CoreConfig.EMPTY)
                .getConfigValue();
    }

    public String getConfigValue(String configKey) {
        return getCoreConfigs()
                .stream()
                .filter(config -> StringUtil.equalsNotNull(configKey, config.getConfigKey()) &&
                        StringUtil.equalsNotNull("ALL", config.getOrgId())
                )
                .findFirst()
                .orElse(CoreConfig.EMPTY)
                .getConfigValue();
    }

    public boolean isWatzapSendEnable(String orgId) {
        String configValue = getConfigValue(CoreConstant.ConfigKey.WATZAP_SEND_ENABLE, orgId);
        return Boolean.parseBoolean(configValue);
    }

    public String getWatzapApiKey(String orgId) {
        return getConfigValue(CoreConstant.ConfigKey.WATZAP_API_KEY, orgId);
    }

    public String getWatzapNumberKey(String orgId) {
        return getConfigValue(CoreConstant.ConfigKey.WATZAP_NUMBER_KEY, orgId);
    }

    public String getWatzapApiUri() {
        return getConfigValue(CoreConstant.ConfigKey.WATZAP_API_URI);
    }

    public String getCoreAreaLevelRoot(String orgId) {
        return getConfigValue(CoreConstant.ConfigKey.CORE_AREA_LEVEL_ROOT, orgId);
    }

    public List<String> getCoreAreaRootIds(String orgId) {
        String configValue = getConfigValue(CoreConstant.ConfigKey.CORE_AREA_ROOT_IDS, orgId);
        return Arrays.asList(configValue.split(","));
    }

    @Cacheable("coreConfig")
    public List<CoreConfig> getCoreConfigs() {
        return coreConfigRepository.findAll()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }
}