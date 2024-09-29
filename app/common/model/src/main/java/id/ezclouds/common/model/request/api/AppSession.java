/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.api;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSession.java, v 0.1 2024‐09‐29 12:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppSession {

    private String memberSessionId;
    private String deviceId;

    public String getMemberSessionId() {
        return memberSessionId;
    }

    public void setMemberSessionId(String memberSessionId) {
        this.memberSessionId = memberSessionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}