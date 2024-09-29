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
 * @version $Id: AuthMemberClientSessionDO.java, v 0.1 2024‐09‐30 1:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_auth_member_client_session")
public class AuthMemberClientSessionDO {

    @Id
    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "shard")
    private String shard;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_roles")
    private String memberRoles;

    @Column(name = "device_id")
    private String deviceId;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getShard() {
        return shard;
    }

    public void setShard(String shard) {
        this.shard = shard;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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