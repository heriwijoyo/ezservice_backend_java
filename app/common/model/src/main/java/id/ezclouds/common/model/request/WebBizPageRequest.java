/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizPageRequest.java, v 0.1 2024‐08‐11 12:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebBizPageRequest extends BizPageRequest {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}