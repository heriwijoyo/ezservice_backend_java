/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPublicSession.java, v 0.1 2024‐10‐10 12:27 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebPublicSession implements AuthSession {

    private String sessionId;
    private String orgId;
    private AuthScene authScene;
    private List<AuthRole> authRoles;

    private String sessionCode;
    private String createdTime;
    private String expiryTime;

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
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

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setAuthScene(AuthScene authScene) {
        this.authScene = authScene;
    }

    public void setAuthRoles(List<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public AuthScene getAuthScene() {
        return authScene;
    }

    @Override
    public String getOrgId() {
        return orgId;
    }

    @Override
    public String getOrgCode() {
        return null;
    }

    @Override
    public List<AuthRole> getAuthRoles() {
        return authRoles;
    }
}