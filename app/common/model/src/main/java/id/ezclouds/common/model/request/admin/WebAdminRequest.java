/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.admin;

import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAdminRequest.java, v 0.1 2024‐08‐17 7:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebAdminRequest extends BizRequest {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}