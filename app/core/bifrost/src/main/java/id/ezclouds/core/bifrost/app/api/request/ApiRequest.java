/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

import id.ezclouds.core.bifrost.core.BaseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBaseRequest.java, v 0.1 2023‐12‐09 10:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiRequest extends BaseRequest {

    private RequestAppClient appClient;

    private RequestAppSession appSession;

    private Map<String, String> extendInfo = new HashMap<>();

    public RequestAppClient getAppClient() {
        return appClient;
    }

    public void setAppClient(RequestAppClient requestAppClient) {
        this.appClient = requestAppClient;
    }

    public RequestAppSession getAppSession() {
        return appSession;
    }

    public void setAppSession(RequestAppSession appSession) {
        this.appSession = appSession;
    }

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }
}