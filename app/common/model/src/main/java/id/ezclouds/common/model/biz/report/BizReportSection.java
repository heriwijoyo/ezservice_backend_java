/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportSection.java, v 0.1 2024‐09‐11 1:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizReportSection {

    VOTER_BASE_SUMMARY("voter"),
    VOTER_BASE_PROGRESSIVE("voterProgress"),
    VOTER_BASE_AREA("voterArea"),

    ;

    private final String code;

    BizReportSection(String code) {
        this.code = code;
    }

    public static BizReportSection getByCode(String code) {
        for (BizReportSection section : values()) {
            if (section.code.equals(code)) {
                return section;
            }
        }
        return null;
    }
}