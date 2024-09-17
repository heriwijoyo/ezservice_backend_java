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
    TOTAL_TPS("TOTAL_TPS"),
    MEMBER_TODAY("MEMBER_TODAY"),
    MEMBER_YESTERDAY("MEMBER_YESTERDAY"),

    WORKSPACE_REGENCY("WORKSPACE_REGENCY"),
    WORKSPACE_DISTRICT("WORKSPACE_DISTRICT"),
    WORKSPACE_VILLAGE("WORKSPACE_VILLAGE"),
    WORKSPACE_VOTE_STATION("WORKSPACE_VOTE_STATION"),

    VOTER_BASE_CLUSTER("VOTER_BASE_CLUSTER"),
    VOTER_BASE_MEMBER("VOTER_BASE_MEMBER"),
    VOTER_BASE_VOTER("VOTER_BASE_VOTER"),
    VOTER_BASE_VOTE_STATION("VOTER_BASE_VOTE_STATION"),

    REAL_COUNT_VOTE_STATION("REAL_COUNT_VOTE_STATION"),
    REAL_COUNT_FIXED_VOTER("REAL_COUNT_FIXED_VOTER"),
    REAL_COUNT_VOTER_ALL("REAL_COUNT_VOTER_ALL"),
    REAL_COUNT_VOTER_VERIFIED("REAL_COUNT_VOTER_VERIFIED"),

    ;
    private final String code;

    BizReportOverallKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}