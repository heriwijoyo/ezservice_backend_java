/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.websocket.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SessionIdentity.java, v 0.1 2024‐09‐09 10:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SessionIdentity {

    private String sessionId;
    private String orgId;
    private String orgCode;

    public SessionIdentity(String sessionId, String orgId, String orgCode) {
        this.sessionId = sessionId;
        this.orgId = orgId;
        this.orgCode = orgCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }
}