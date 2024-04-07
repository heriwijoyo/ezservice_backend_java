/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.context;

import id.ezclouds.common.util.HashUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBizContext.java, v 0.1 2023‐12‐09 9:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAppContext {

    private EzAppEvent ezAppEvent;
    private long startTimeMilis;
    private String traceId;
    private String orgId;
    private String orgCode;
    private Map<String, String> orgExtendConfig = new HashMap<>();
    private String appId;
    private String deviceId;
    private int appVersionNo;
    private String memberSessionId;
    private String errorStackTrace;

    public EzAppContext(EzAppEvent ezAppEvent) {
        this.ezAppEvent = ezAppEvent;
        this.traceId = HashUtil.createHash(UUID.randomUUID().toString());
        this.startTimeMilis = System.currentTimeMillis();
    }

    public EzAppEvent getEzAppEvent() {
        return ezAppEvent;
    }

    public String getTimeCost() {
        long currentTimeMilis = System.currentTimeMillis();
        long timeCostMilis = currentTimeMilis - startTimeMilis;
        return String.valueOf(timeCostMilis);
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

    public Map<String, String> getOrgExtendConfig() {
        return orgExtendConfig;
    }

    public void setOrgExtendConfig(Map<String, String> orgExtendConfig) {
        if (orgExtendConfig != null) {
            this.orgExtendConfig = orgExtendConfig;
        }
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

    public int getAppVersionNo() {
        return appVersionNo;
    }

    public void setAppVersionNo(int appVersionNo) {
        this.appVersionNo = appVersionNo;
    }

    public String getMemberSessionId() {
        return memberSessionId;
    }

    public void setMemberSessionId(String memberSessionId) {
        this.memberSessionId = memberSessionId;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public void appendErrorStackTrace(String errorStackTrace) {
        if (this.errorStackTrace == null) {
            this.errorStackTrace = errorStackTrace;
        } else {
            this.errorStackTrace += "\n" + errorStackTrace;
        }
    }
}