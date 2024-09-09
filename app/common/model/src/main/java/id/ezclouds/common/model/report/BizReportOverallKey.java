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

    BG_REGENCY("BG_REGENCY"),
    BG_DISTRICT("BG_DISTRICT"),
    BG_VILLAGE("BG_VILLAGE"),
    BG_VOTE_STATION("BG_VOTE_STATION"),

    VB_CLUSTER("VB_CLUSTER"),
    VB_MEMBER("VB_MEMBER"),
    VB_VOTER("VB_VOTER"),
    VB_VOTE_STATION("VB_VOTE_STATION"),

    RC_VOTE_STATION("RC_VOTE_STATION"),
    RC_FIXED_VOTER("RC_FIXED_VOTER"),
    RC_VOTER_ALL("RC_VOTER_ALL"),
    RC_VOTER_VERIFIED("RC_VOTER_VERIFIED"),

    ;
    private final String code;

    BizReportOverallKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}