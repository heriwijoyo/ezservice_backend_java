/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request.admin;

import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebBizUpdateRequest.java, v 0.1 2024‐03‐29 4:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebBizUpdateRequest<T> extends BizRequest {

    private String sessionId;
    private String orgId;
    private T object;

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

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}