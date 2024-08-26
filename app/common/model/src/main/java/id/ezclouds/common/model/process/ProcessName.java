/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.process;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessName.java, v 0.1 2024‐08‐19 8:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum ProcessName {

    SURVEY_RESPONSE_PARSE("SURVEY_RESPONSE_PARSE"),
    REPORT_AREA_COMMON_TABLE_PARSE("REPORT_AREA_COMMON_TABLE_PARSE"),
    UNKNOWN("UNKNOWN"),

    ;
    private final String code;

    ProcessName(String code) {
        this.code = code;
    }

    public static ProcessName getByCode(String code) {
        for (ProcessName processName : values()) {
            if (processName.code.equals(code)) {
                return processName;
            }
        }
        return UNKNOWN;
    }
}