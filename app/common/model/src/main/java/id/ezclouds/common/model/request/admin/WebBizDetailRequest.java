/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.admin;

import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizDetailRequest.java, v 0.1 2024‐03‐29 2:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebBizDetailRequest<T> extends BizRequest {

    private String sessionId;
    private T object;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}