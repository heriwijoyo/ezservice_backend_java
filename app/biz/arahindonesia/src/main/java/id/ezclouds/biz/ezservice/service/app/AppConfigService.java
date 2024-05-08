/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.service.core.BizCacheKey;
import id.ezclouds.biz.ezservice.service.app.dataobject.AppBuildPackageDO;
import id.ezclouds.biz.ezservice.service.app.model.AppMessageTemplate;
import id.ezclouds.biz.ezservice.service.app.model.BizAppBuildPackage;
import id.ezclouds.biz.ezservice.service.app.model.BizAppConfig;
import id.ezclouds.biz.ezservice.service.app.repo.AppBuildPackageRepository;
import id.ezclouds.biz.ezservice.service.app.repo.AppCommonMessageTemplateRepository;
import id.ezclouds.biz.ezservice.service.app.dataobject.AppConfigDO;
import id.ezclouds.biz.ezservice.service.app.repo.AppConfigRepository;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private AppBuildPackageRepository appBuildPackageRepository;

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
                AppConstant.CfgKey.REPORT_OPTIONS,
                AppConstant.CfgKey.APP_REQUIRE_LOGIN
        );
    }

    @Transactional
    public void createAppBuildPackage(BizAppBuildPackage buildPackage) throws Exception {
        AppBuildPackageDO buildPackageDO = convert(buildPackage);
        String currentTime = DateUtil.getCurrentFormattedDate();
        buildPackageDO.setId(HashUtil.createHash(buildPackage.getOrgId(), currentTime));
        buildPackageDO.setCreatedTime(currentTime);
        appBuildPackageRepository.saveAndFlush(buildPackageDO);
    }

    public List<BizAppBuildPackage> getAppBuildPackages(String orgId) {
        return appBuildPackageRepository
                .findByOrgId(orgId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public BizAppBuildPackage getBuildPackageByVersionName(String orgId, String platform, String versionName) {
        AppBuildPackageDO packageDO = appBuildPackageRepository
                .findByOrgIdAndPlatformAndVersionName(orgId, platform, versionName);
        if (packageDO == null) {
            return null;
        }
        return convert(packageDO);
    }

    public BizAppBuildPackage getLatestBuildPackage(String orgId, String platform) {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdTime"));
        Page<AppBuildPackageDO> result = appBuildPackageRepository
                .findByOrgIdAndPlatform(orgId, platform, pageRequest);
        if (result != null && result.hasContent()) {
            return convert(result.getContent().get(0));
        }
        return null;
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

                        case AppConstant.CfgKey.REPORT_OPTIONS:
                            appConfig.setReportOptions(cfg.getConfigValue());
                            break;

                        case AppConstant.CfgKey.APP_REQUIRE_LOGIN:
                            appConfig.setAppRequireLogin(Boolean.parseBoolean(cfg.getConfigValue()));
                            break;
                    }
                });
        return appConfig;
    }

    public Map<String, String> getAppConfigMap(String orgId) {
        Map<String, String> configMap = new HashMap<>();
        getAppConfigAllActive()
                .stream()
                .filter(config -> orgId.equals(config.getOrgId()))
                .forEach(cfg -> {
                    configMap.put(cfg.getConfigKey(), cfg.getConfigValue());
                });
        return configMap;
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

    @Cacheable(value = BizCacheKey.APP_CONFIG_ALL)
    public List<AppConfigDO> getAppConfigAllActive() {
        return appConfigRepository.findAllActive();
    }

    @Cacheable(value = BizCacheKey.APP_MESSAGE_TEMPLATE)
    public List<AppMessageTemplate> getMessageTemplates() {
        return appCommonMessageTemplateRepository
                .findAll()
                .stream()
                .map(templateDO -> new AppMessageTemplate(templateDO.getTemplateId(), templateDO.getTemplateValue()))
                .collect(Collectors.toList());
    }

    private AppBuildPackageDO convert(BizAppBuildPackage buildPackage) {
        AppBuildPackageDO buildPackageDO = new AppBuildPackageDO();
        buildPackageDO.setId(buildPackage.getId());
        buildPackageDO.setOrgId(buildPackage.getOrgId());
        buildPackageDO.setPlatform(buildPackage.getPlatform());
        buildPackageDO.setVersionCode(buildPackage.getVersionCode());
        buildPackageDO.setVersionName(buildPackage.getVersionName());
        buildPackageDO.setCreatedTime(buildPackage.getCreatedTime());
        buildPackageDO.setStatus(buildPackage.getStatus());
        return buildPackageDO;
    }

    private BizAppBuildPackage convert(AppBuildPackageDO buildPackageDO) {
        BizAppBuildPackage buildPackage = new BizAppBuildPackage();
        buildPackage.setId(buildPackageDO.getId());
        buildPackage.setOrgId(buildPackageDO.getOrgId());
        buildPackage.setPlatform(buildPackageDO.getPlatform());
        buildPackage.setVersionCode(buildPackageDO.getVersionCode());
        buildPackage.setVersionName(buildPackageDO.getVersionName());
        buildPackage.setCreatedTime(buildPackageDO.getCreatedTime());
        buildPackage.setStatus(buildPackageDO.getStatus());
        return buildPackage;
    }
}