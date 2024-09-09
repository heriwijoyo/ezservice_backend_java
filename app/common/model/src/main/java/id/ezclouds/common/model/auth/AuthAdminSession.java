/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthAdminSession.java, v 0.1 2024‐02‐10 4:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AuthAdminSession implements AuthSession {

    private String sessionId;
    private String sessionCode;
    private String scene;
    private String loginTime;
    private String orgId;
    private String orgCode;
    private String appId;
    private String clientId;
    private String deviceId;
    private String memberId;
    private String memberRoles;
    private String createdTime;
    private String expiryTime;
    private int status;

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public AuthScene getAuthScene() {
        return AuthScene.getByCode(scene);
    }

    @Override
    public String getOrgId() {
        return orgId;
    }

    @Override
    public String getOrgCode() {
        return orgCode;
    }

    @Override
    public List<AuthRole> getAuthRoles() {
        return Arrays.asList(memberRoles.split(","))
                .stream()
                .map(role -> AuthRole.getByCode(role))
                .collect(Collectors.toList());
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberRoles() {
        return memberRoles;
    }

    public void setMemberRoles(String memberRoles) {
        this.memberRoles = memberRoles;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}