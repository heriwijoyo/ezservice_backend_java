/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.common.dal.model.Organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBizContext.java, v 0.1 2023‐12‐09 9:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAppContext {

    private EzAppEvent ezAppEvent;
    private String traceId;

    private Organization organization;

    public EzAppContext(EzAppEvent ezAppEvent) {
        this.ezAppEvent = ezAppEvent;
        this.traceId = String.valueOf(System.currentTimeMillis());
    }

    public String getTraceId() {
        return traceId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}