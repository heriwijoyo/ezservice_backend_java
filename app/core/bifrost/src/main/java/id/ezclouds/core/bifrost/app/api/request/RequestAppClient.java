/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAppClient.java, v 0.1 2023‐12‐09 4:10 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class RequestAppClient {

    private String organizationId;
    private String applicationId;
    private String clientId;
    private String clientSecret;
    private String deviceId;
    private Integer appVersionNo;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getAppVersionNo() {
        if (appVersionNo == null) {
            return 0;
        }
        return appVersionNo;
    }

    public void setAppVersionNo(Integer appVersionNo) {
        this.appVersionNo = appVersionNo;
    }
}