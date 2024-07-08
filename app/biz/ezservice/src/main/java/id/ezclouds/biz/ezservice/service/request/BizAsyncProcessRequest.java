/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.biz.ezservice.enums.BizAsyncScene;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncProcessRequest.java, v 0.1 2024‐07‐06 12:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAsyncProcessRequest {

    private String orgId;
    private BizAsyncScene bizAsyncScene;
    private Map<String, Object> payload = new HashMap<>();
    private String processTime;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public BizAsyncScene getBizAsyncScene() {
        return bizAsyncScene;
    }

    public void setBizAsyncScene(BizAsyncScene bizAsyncScene) {
        this.bizAsyncScene = bizAsyncScene;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }
}