/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfig.java, v 0.1 2023‐12‐07 2:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppConfig {

    private String appName;
    private String androidVersionName;
    private int androidVersionCode;
    private String androidUpdateUrl;
    private String androidUpdateApk;
    private boolean androidForceUpdate;
    private int bizMaxTpsNumber;
    private String reportOptions;
    private boolean appRequireLogin;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAndroidVersionName() {
        return androidVersionName;
    }

    public void setAndroidVersionName(String androidVersionName) {
        this.androidVersionName = androidVersionName;
    }

    public int getAndroidVersionCode() {
        return androidVersionCode;
    }

    public void setAndroidVersionCode(int androidVersionCode) {
        this.androidVersionCode = androidVersionCode;
    }

    public String getAndroidUpdateUrl() {
        return androidUpdateUrl;
    }

    public void setAndroidUpdateUrl(String androidUpdateUrl) {
        this.androidUpdateUrl = androidUpdateUrl;
    }

    public String getAndroidUpdateApk() {
        return androidUpdateApk;
    }

    public void setAndroidUpdateApk(String androidUpdateApk) {
        this.androidUpdateApk = androidUpdateApk;
    }

    public boolean isAndroidForceUpdate() {
        return androidForceUpdate;
    }

    public void setAndroidForceUpdate(boolean androidForceUpdate) {
        this.androidForceUpdate = androidForceUpdate;
    }

    public int getBizMaxTpsNumber() {
        return bizMaxTpsNumber;
    }

    public void setBizMaxTpsNumber(int bizMaxTpsNumber) {
        this.bizMaxTpsNumber = bizMaxTpsNumber;
    }

    public String getReportOptions() {
        return reportOptions;
    }

    public void setReportOptions(String reportOptions) {
        this.reportOptions = reportOptions;
    }

    public boolean isAppRequireLogin() {
        return appRequireLogin;
    }

    public void setAppRequireLogin(boolean appRequireLogin) {
        this.appRequireLogin = appRequireLogin;
    }
}