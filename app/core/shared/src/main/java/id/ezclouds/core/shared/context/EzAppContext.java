/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.context;

import id.ezclouds.core.shared.model.Organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBizContext.java, v 0.1 2023‐12‐09 9:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAppContext {

    private EzAppEvent ezAppEvent;
    private String traceId;
    private String orgId;
    private String appId;

    public EzAppContext(EzAppEvent ezAppEvent) {
        this.ezAppEvent = ezAppEvent;
        this.traceId = String.valueOf(System.currentTimeMillis());
    }

    public String getTraceId() {
        return traceId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}