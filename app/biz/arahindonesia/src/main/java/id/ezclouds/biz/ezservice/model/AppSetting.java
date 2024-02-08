/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSetting.java, v 0.1 2023‐12‐09 10:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppSetting {

    private AppConfig appConfig;
    private HomeData homeData;

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public HomeData getHomeData() {
        return homeData;
    }

    public void setHomeData(HomeData homeData) {
        this.homeData = homeData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}