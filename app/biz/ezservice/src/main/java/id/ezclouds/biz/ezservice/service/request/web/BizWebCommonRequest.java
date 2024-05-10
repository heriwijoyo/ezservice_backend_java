/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request.web;

import id.ezclouds.biz.ezservice.service.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizWebCommonRequest.java, v 0.1 2024‐05‐01 10:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizWebCommonRequest extends BizRequest {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}