/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor.event;

import id.ezclouds.common.util.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessEvent.java, v 0.1 2024‐07‐15 2:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizProcessEvent implements EzAppEvent {

    SYNC_MEMBER_UNION("SYNC_MEMBER_UNION"),
    GENERATE_REPORT_BY_AREA("GENERATE_REPORT_BY_AREA"),
    GENERATE_REPORT_BY_SUB_ORG("GENERATE_REPORT_BY_SUB_ORG"),
    GENERATE_REPORT_TIME_SERIES("GENERATE_REPORT_TIME_SERIES"),
    SCHEDULER_MINUTE("SCHEDULER_MINUTE"),
    SUB_ORG_DAILY_MONITOR("SUB_ORG_DAILY_MONITOR"),
    DAILY_CHECK_NO_SUB_ORG("DAILY_CHECK_NO_SUB_ORG"),

    SAMPLE_ONE("SAMPLE_ONE"),
    SAMPLE_TWO("SAMPLE_TWO"),
    SAMPLE_THREE("SAMPLE_THREE"),

    UNKNOWN("UNKNOWN"),
    ;
    private final String code;

    BizProcessEvent(String code) {
        this.code = code;
    }

    @Override
    public String getEventCode() {
        return code;
    }
}