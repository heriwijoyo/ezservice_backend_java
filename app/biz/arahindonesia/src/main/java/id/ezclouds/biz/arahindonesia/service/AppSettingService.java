/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service;

import id.ezclouds.biz.arahindonesia.config.AppConfig;
import id.ezclouds.biz.arahindonesia.model.AppSetting;
import id.ezclouds.common.dal.model.AppConfigDO;
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

    public AppSetting getSetting() {
        List<AppConfigDO> appConfigDOList = appConfigService.getAppConfigs();

        for (AppConfigDO appConfigDO : appConfigDOList) {

        }

        AppSetting appSetting = new AppSetting();
        appSetting.setAppConfig(new AppConfig());

        return appSetting;
    }
}