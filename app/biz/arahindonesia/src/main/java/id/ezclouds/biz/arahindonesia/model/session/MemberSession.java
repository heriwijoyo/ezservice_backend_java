/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model.session;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberSession.java, v 0.1 2023‐12‐11 11:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberSession {

    private String sessionId;
    private String expiryTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "MemberSession{" +
                "sessionId='" + sessionId + '\'' +
                ", expiryTime='" + expiryTime + '\'' +
                '}';
    }
}