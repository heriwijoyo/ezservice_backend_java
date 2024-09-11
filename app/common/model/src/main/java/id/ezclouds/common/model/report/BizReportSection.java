/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportSection.java, v 0.1 2024‐09‐11 1:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizReportSection {

    PROJECT_AREA_BASE("PROJECT_AREA_BASE"),
    VOTER_AREA_BASE("VOTER_AREA_BASE"),
    VOTE_REAL_COUNT("VOTE_REAL_COUNT"),

    ;

    private final String code;

    BizReportSection(String code) {
        this.code = code;
    }
}