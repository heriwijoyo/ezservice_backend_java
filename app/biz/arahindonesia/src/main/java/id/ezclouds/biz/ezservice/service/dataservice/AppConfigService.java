/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice;

import id.ezclouds.biz.ezservice.constant.AppConstant;
import id.ezclouds.biz.ezservice.model.AppConfig;
import id.ezclouds.biz.ezservice.service.dataservice.model.AppMessageTemplate;
import id.ezclouds.biz.ezservice.service.dataservice.repo.AppCommonMessageTemplateRepository;
import id.ezclouds.common.dal.dataobject.AppConfigDO;
import id.ezclouds.common.dal.repo.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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