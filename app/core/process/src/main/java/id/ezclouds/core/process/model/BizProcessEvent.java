/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.model;

import id.ezclouds.common.util.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessEvent.java, v 0.1 2024‐07‐28 4:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizProcessEvent implements EzAppEvent {

    MEMBER_IMPORT_CSV("MEMBER_IMPORT_CSV", OverlapMode.SERIAL),

    GENERATE_REPORT_OVERALL("GENERATE_REPORT_OVERALL", OverlapMode.SERIAL),
    GENERATE_REPORT_MEMBER_TODAY("GENERATE_REPORT_MEMBER_TODAY", OverlapMode.SERIAL),

    SURVEY_RESPONSE_PARSE("SURVEY_RESPONSE_PARSE", OverlapMode.SERIAL),
    REPORT_AREA_COMMON_TABLE_PARSE("REPORT_AREA_COMMON_TABLE_PARSE", OverlapMode.SERIAL),

    INIT_MASTER_DATA_AREA("INIT_MASTER_DATA_AREA", OverlapMode.SERIAL),
    INIT_REPORT_ACCUMULATE_AREA("INIT_REPORT_ACCUMULATE_AREA", OverlapMode.SERIAL),
    REPORT_DATA_INIT("REPORT_DATA_INIT", OverlapMode.SERIAL),


    ORG_INIT_MIGRATE_MEMBER("ORG_INIT_MIGRATE_MEMBER", OverlapMode.ONLY_SINGLE),
    BIZ_DEBUGGER("BIZ_DEBUGGER", OverlapMode.SERIAL),

    UNKNOWN("UNKNOWN", OverlapMode.SERIAL),
    ;
    private final String code;
    private final OverlapMode overlapMode;

    BizProcessEvent(String code, OverlapMode overlapMode) {
        this.code = code;
        this.overlapMode = overlapMode;
    }

    @Override
    public String getEventCode() {
        return code;
    }
}