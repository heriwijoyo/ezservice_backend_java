/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberCommonSessionRequest.java, v 0.1 2024‐02‐04 11:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMemberCommonSessionRequest {

    private String orgId;
    private String appId;
    private String loginType;
    private String loginId;
    private String deviceId;
    private String scene;
    private String verifyStrategy;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getVerifyStrategy() {
        return verifyStrategy;
    }

    public void setVerifyStrategy(String verifyStrategy) {
        this.verifyStrategy = verifyStrategy;
    }
}