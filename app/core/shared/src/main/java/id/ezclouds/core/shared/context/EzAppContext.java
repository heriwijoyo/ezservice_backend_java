/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.context;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBizContext.java, v 0.1 2023‐12‐09 9:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAppContext {

    private EzAppEvent ezAppEvent;
    private String traceStartTime;
    private String traceId;
    private String orgId;
    private String orgCode;
    private String appId;
    private String deviceId;

    public EzAppContext(EzAppEvent ezAppEvent) {
        this.traceStartTime = DateUtil.getCurrentFormattedDate();
        this.ezAppEvent = ezAppEvent;
        this.traceId = HashUtil.createHash(this.traceStartTime);
    }

    public EzAppEvent getEzAppEvent() {
        return ezAppEvent;
    }

    public String getTraceStartTime() {
        return traceStartTime;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}