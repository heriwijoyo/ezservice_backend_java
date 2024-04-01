/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppMessageTemplate;
import id.ezclouds.biz.ezservice.service.dataservice.model.BizAppConfig;
import id.ezclouds.biz.ezservice.service.dataservice.repo.AppCommonMessageTemplateRepository;
import id.ezclouds.biz.ezservice.service.dataservice.dataobject.AppConfigDO;
import id.ezclouds.biz.ezservice.service.dataservice.repo.AppConfigRepository;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfigService.java, v 0.1 2023‐12‐09 11:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Autowired
    private AppCommonMessageTemplateRepository appCommonMessageTemplateRepository;

    private static final List<String> APP_CONFIG_KEYS;

    static {
        APP_CONFIG_KEYS = Arrays.asList(
                AppConstant.CfgKey.APP_NAME,
                AppConstant.CfgKey.ANDROID_VERSION_NAME,
                AppConstant.CfgKey.ANDROID_VERSION_CODE,
                AppConstant.CfgKey.ANDROID_UPDATE_URL,
                AppConstant.CfgKey.ANDROID_UPDATE_APK,
                AppConstant.CfgKey.ANDROID_FORCE_UPDATE,
                AppConstant.CfgKey.BIZ_MAX_TPS_NUMBER,
                AppConstant.CfgKey.REPORT_OPTIONS
        );
    }

    public AppConfig getAppConfig(String orgId) {
        final AppConfig appConfig = new AppConfig();

        getAppConfigAllActive()
                .stream()
                .filter(config -> orgId.equals(config.getOrgId()))
                .forEach(cfg -> {
                    switch (cfg.getConfigKey()) {
                        case AppConstant.CfgKey.APP_NAME:
                            appConfig.setAppName(cfg.getConfigValue());
                            break;

                        case AppConstant.CfgKey.ANDROID_VERSION_NAME:
                            appConfig.setAndroidVersionName(cfg.getConfigValue());
                            break;

                        case AppConstant.CfgKey.ANDROID_VERSION_CODE:
                            appConfig.setAndroidVersionCode(Integer.parseInt(cfg.getConfigValue()));
                            break;

                        case AppConstant.CfgKey.ANDROID_UPDATE_URL:
                            appConfig.setAndroidUpdateUrl(cfg.getConfigValue());
                            break;

                        case AppConstant.CfgKey.ANDROID_UPDATE_APK:
                            appConfig.setAndroidUpdateApk(cfg.getConfigValue());
                            break;

                        case AppConstant.CfgKey.ANDROID_FORCE_UPDATE:
                            appConfig.setAndroidForceUpdate(Boolean.parseBoolean(cfg.getConfigValue()));
                            break;

                        case AppConstant.CfgKey.BIZ_MAX_TPS_NUMBER:
                            appConfig.setBizMaxTpsNumber(Integer.parseInt(cfg.getConfigValue()));
                            break;
                    }
                });
        return appConfig;
    }

    public String getMessageTemplate(String templateId) {
        return getMessageTemplates()
                .stream()
                .filter(template -> templateId.equals(template.getId()))
                .findFirst()
                .orElse(new AppMessageTemplate(null, null))
                .getValue();
    }

    public List<BizAppConfig> getAppConfigByOrgId(String orgId) {
        List<BizAppConfig> bizAppConfigs = new ArrayList<>();
        List<AppConfigDO> appConfigDOList = appConfigRepository
                .findByOrgId(orgId);

        for (String configKey : APP_CONFIG_KEYS) {
            String configValue = StringUtil.EMPTY;
            int configStatus = 0;
            for (AppConfigDO configDO : appConfigDOList) {
                if (configKey.equals(configDO.getConfigKey())) {
                    configValue = configDO.getConfigValue();
                    configStatus = configDO.getStatus();
                }
            }
            BizAppConfig bizAppConfig = new BizAppConfig();
            bizAppConfig.setConfigKey(configKey);
            bizAppConfig.setConfigValue(configValue);
            bizAppConfig.setStatus(configStatus);
            bizAppConfigs.add(bizAppConfig);
        }

        return bizAppConfigs;
    }

    @Transactional
    public void saveBizAppConfig(String orgId, BizAppConfig bizAppConfig) {
        AppConfigDO configDO = appConfigRepository
                .findByOrgIdAndConfigKey(orgId, bizAppConfig.getConfigKey());
        if (configDO == null) {
            configDO = new AppConfigDO();
            configDO.setId(HashUtil.createHash(orgId, bizAppConfig.getConfigKey()));
            configDO.setOrgId(orgId);
            configDO.setConfigKey(bizAppConfig.getConfigKey());
        }
        configDO.setConfigValue(bizAppConfig.getConfigValue());
        configDO.setStatus(bizAppConfig.getStatus());
        appConfigRepository.saveAndFlush(configDO);
    }

    @Cacheable("appConfigAllActive")
    public List<AppConfigDO> getAppConfigAllActive() {
        return appConfigRepository.findAllActive();
    }

    @Cacheable("appMessageTemplate")
    public List<AppMessageTemplate> getMessageTemplates() {
        return appCommonMessageTemplateRepository
                .findAll()
                .stream()
                .map(templateDO -> new AppMessageTemplate(templateDO.getTemplateId(), templateDO.getTemplateValue()))
                .collect(Collectors.toList());
    }
}