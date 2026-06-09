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

    INIT_SEQUENCE_CONFIG("INIT_SEQUENCE_CONFIG"),
    INIT_MASTER_DATA_AREA("INIT_MASTER_DATA_AREA"),
    INIT_REPORT_OVERALL("INIT_REPORT_OVERALL"),
    INIT_REPORT_ACCUMULATE_AREA("INIT_REPORT_ACCUMULATE_AREA"),
    INIT_MIGRATE_MEMBER("INIT_MIGRATE_MEMBER"),

    INIT_REPORT_REAL_COUNT_OVERALL("INIT_REPORT_REAL_COUNT_OVERALL"),
    INIT_REPORT_REAL_COUNT_AREA("INIT_REPORT_REAL_COUNT_AREA"),

    SURVEY_RESPONSE_PROCESS("SURVEY_RESPONSE_PROCESS"),

    GENERATE_PUBLIC_SESSION("GENERATE_PUBLIC_SESSION"),
    GENERATE_REPORT_ACCUMULATE_TIME_SERIES("GENERATE_REPORT_ACCUMULATE_TIME_SERIES"),

    TMP_RECOVER_ACCUMULATE_AREA("TMP_RECOVER_ACCUMULATE_AREA"),

    ;
    private final String code;

    ProcessName(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ProcessName getByCode(String code) {
        for (ProcessName processName : values()) {
            if (processName.code.equals(code)) {
                return processName;
            }
        }
        return null;
    }
}