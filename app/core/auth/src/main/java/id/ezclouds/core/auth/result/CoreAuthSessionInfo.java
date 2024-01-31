/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthSessionInfo.java, v 0.1 2024‐01‐29 4:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAuthSessionInfo {
    private String sessionId;
    private String memberId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}