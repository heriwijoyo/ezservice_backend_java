/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfig.java, v 0.1 2023‐12‐07 2:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppConfig {

    private String appName = "RJL";
    private int versionCode = 1;
    private String versionName = "1.0.0";
    private String updateUrl = "https://files-rjl.arahindonesia.id/android/RJLApp_1.apk";

    private int sliderAnimationDuration = 200;
    private int maxTpsNumber = 50;

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

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
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
}