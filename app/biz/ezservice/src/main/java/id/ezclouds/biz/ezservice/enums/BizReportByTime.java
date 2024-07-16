/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByTime.java, v 0.1 2024‐07‐17 1:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizReportByTime {

    RJL_DAILY_SUB_ORG_PERFORMANCE("RJL_DAILY_SUB_ORG_PERFORMANCE", "RJL0", BizTimePeriod.DAILY),

    ;

    private final String id;
    private final String orgId;
    private final BizTimePeriod bizTimePeriod;

    BizReportByTime(String id, String orgId, BizTimePeriod bizTimePeriod) {
        this.id = id;
        this.orgId = orgId;
        this.bizTimePeriod = bizTimePeriod;
    }

    public String getId() {
        return id;
    }

    public String getOrgId() {
        return orgId;
    }

    public BizTimePeriod getBizTimePeriod() {
        return bizTimePeriod;
    }
}