/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model;

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
}