/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request.web;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizWebUpdateRequest.java, v 0.1 2024‐03‐29 4:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizWebUpdateRequest<T> {

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