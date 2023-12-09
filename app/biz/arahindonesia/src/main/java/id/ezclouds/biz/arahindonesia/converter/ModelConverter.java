/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.model.AppClient;
import id.ezclouds.biz.arahindonesia.model.AppConfig;
import id.ezclouds.biz.arahindonesia.model.AppUpdateInfo;
import id.ezclouds.common.dal.model.AppClientDO;
import id.ezclouds.common.dal.model.AppConfigDO;
import id.ezclouds.common.dal.model.OrganizationDO;
import id.ezclouds.core.shared.model.Organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ModelConverter.java, v 0.1 2023‐12‐10 12:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class ModelConverter {

    public static Organization convert(OrganizationDO organizationDO) {
        if (organizationDO == null) {
            return null;
        }
        return new Organization(organizationDO.getOrgId(), organizationDO.getName());
    }

    public static AppClient convert(AppClientDO appClientDO) {
        if (appClientDO == null) {
            return null;
        }
        AppClient appClient = new AppClient();
        appClient.setOrgId(appClientDO.getOrgId());
        appClient.setAppId(appClientDO.getAppId());
        appClient.setClientId(appClientDO.getClientId());
        appClient.setClientSecret(appClientDO.getClientSecret());
        appClient.setStatus(appClientDO.getStatus());
        return appClient;
    }

    public static AppConfig convert(AppConfigDO appConfigDO) {
        if (appConfigDO == null) {
            return null;
        }
        AppConfig appConfig = new AppConfig();
        appConfig.setAppName(appConfigDO.getAppName());
        appConfig.setVersionCode(appConfigDO.getVersionCode());
        appConfig.setVersionName(appConfigDO.getVersionName());
        appConfig.setSliderAnimationDuration(appConfigDO.getSliderAnimationDuration());
        appConfig.setMaxTpsNumber(appConfigDO.getMaxTpsNumber());

        AppUpdateInfo appUpdateInfo = new AppUpdateInfo();
        appUpdateInfo.setTitle(AppConstant.APP_UPDATE_TITLE);
        appUpdateInfo.setMessage(AppConstant.APP_UPDATE_MESSAGE.replace(AppConstant.APP_VERSION_NAME_TAG, appConfigDO.getVersionName()));
        appUpdateInfo.setNeedForceUpdate(appConfigDO.getNeedForceUpdate() == 1);
        appUpdateInfo.setUpdateUrl(appConfigDO.getUpdateUrl());

        appConfig.setAppUpdateInfo(appUpdateInfo);

        return appConfig;
    }
}