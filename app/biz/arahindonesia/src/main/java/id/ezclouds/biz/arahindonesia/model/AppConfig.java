/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfig.java, v 0.1 2023‐12‐07 2:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppConfig {

    private String appName;
    private String versionName;
    private int versionCode;
    private int sliderAnimationDuration;
    private int maxTpsNumber;
    private AppUpdateInfo appUpdateInfo;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getSliderAnimationDuration() {
        return sliderAnimationDuration;
    }

    public void setSliderAnimationDuration(int sliderAnimationDuration) {
        this.sliderAnimationDuration = sliderAnimationDuration;
    }

    public int getMaxTpsNumber() {
        return maxTpsNumber;
    }

    public void setMaxTpsNumber(int maxTpsNumber) {
        this.maxTpsNumber = maxTpsNumber;
    }

    public AppUpdateInfo getAppUpdateInfo() {
        return appUpdateInfo;
    }

    public void setAppUpdateInfo(AppUpdateInfo appUpdateInfo) {
        this.appUpdateInfo = appUpdateInfo;
    }
}