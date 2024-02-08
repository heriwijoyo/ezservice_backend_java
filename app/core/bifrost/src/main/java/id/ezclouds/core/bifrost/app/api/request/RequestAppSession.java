/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: RequestAppSession.java, v 0.1 2024‐02‐01 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class RequestAppSession {

    private String memberSessionCode;
    private String memberSessionId;
    private String deviceId;

    public String getMemberSessionCode() {
        return memberSessionCode;
    }

    public void setMemberSessionCode(String memberSessionCode) {
        this.memberSessionCode = memberSessionCode;
    }

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