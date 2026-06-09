/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.enums;

import id.ezclouds.common.util.enums.BizTimePeriod;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByTime.java, v 0.1 2024‐07‐17 1:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizReportByTime {

    DAILY_SUB_ORG_PERFORMANCE("DAILY_SUB_ORG_PERFORMANCE", BizTimePeriod.DAILY),
    DAILY_AREA_PERFORMANCE("DAILY_AREA_PERFORMANCE", BizTimePeriod.DAILY),

    ;

    private final String id;
    private final BizTimePeriod bizTimePeriod;

    BizReportByTime(String id, BizTimePeriod bizTimePeriod) {
        this.id = id;
        this.bizTimePeriod = bizTimePeriod;
    }

    public String getId() {
        return id;
    }

    public BizTimePeriod getBizTimePeriod() {
        return bizTimePeriod;
    }
}