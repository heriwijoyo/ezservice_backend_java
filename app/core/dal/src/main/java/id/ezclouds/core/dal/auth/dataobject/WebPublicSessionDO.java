/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPublicSessionDO.java, v 0.1 2024‐10‐10 12:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_auth_admin_common_session")
public class WebPublicSessionDO {

    @Id
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "session_code")
    private String sessionCode;
    @Column(name = "scene")
    private String scene;
    @Column(name = "login_time")
    private String loginTime;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "org_code")
    private String orgCode;
    @Column(name = "app_id")
    private String appId;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "member_roles")
    private String memberRoles;
    @Column(name = "created_time")
    private String createdTime;
    @Column(name = "expiry_time")
    private String expiryTime;
    @Column(name = "status")
    private int status;

    public String getSessionId() {
        return sessionId;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
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