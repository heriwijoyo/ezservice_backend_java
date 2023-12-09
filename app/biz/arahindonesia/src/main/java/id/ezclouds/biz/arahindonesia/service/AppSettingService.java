/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.AppConfig;
import id.ezclouds.biz.arahindonesia.model.AppSetting;
import id.ezclouds.common.dal.model.AppConfigDO;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSettingService.java, v 0.1 2023‐12‐09 12:53 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppSettingService {

    @Autowired
    private AppConfigService appConfigService;

    public AppSetting getAppSetting() {
        Organization currentOrg = EzAppContextHolder.getOrganization();
        AppConfig appConfig = new AppConfig();

        List<AppConfigDO> appConfigDOList = appConfigService.getAppConfigs();
        for (AppConfigDO appConfigDO : appConfigDOList) {
            if (StringUtil.equalsNotNull(currentOrg.getOrgId(), appConfigDO.getOrgId())) {
                appConfig = ModelConverter.convert(appConfigDO);
            }
        }

        AppSetting appSetting = new AppSetting();
        appSetting.setAppConfig(appConfig);

        return appSetting;
    }
}