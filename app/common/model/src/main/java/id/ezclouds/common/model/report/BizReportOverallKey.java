/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallKey.java, v 0.1 2024‐07‐28 8:35 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizReportOverallKey {

    TOTAL_MEMBER_UNION("TOTAL_MEMBER_UNION"),
    TOTAL_SUB_ORGANIZATION("TOTAL_SUB_ORGANIZATION"),

    ;
    private final String code;

    BizReportOverallKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}