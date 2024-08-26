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

    MEMBER_IMPORT_CSV("MEMBER_IMPORT_CSV"),

    GENERATE_REPORT_OVERALL("GENERATE_REPORT_OVERALL"),
    GENERATE_REPORT_MEMBER_TODAY("GENERATE_REPORT_MEMBER_TODAY"),

    SURVEY_RESPONSE_PARSE("SURVEY_RESPONSE_PARSE"),
    REPORT_AREA_COMMON_TABLE_PARSE("REPORT_AREA_COMMON_TABLE_PARSE"),

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