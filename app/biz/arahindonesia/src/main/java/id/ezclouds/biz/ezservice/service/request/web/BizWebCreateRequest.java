/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request.web;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizWebCreateRequest.java, v 0.1 2024‐03‐27 2:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizWebCreateRequest<T> {

    private String sessionId;
    private T data;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}