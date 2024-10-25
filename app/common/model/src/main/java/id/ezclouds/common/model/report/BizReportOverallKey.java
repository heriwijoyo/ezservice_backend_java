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

    TOTAL_MEMBER_UNION("TOTAL_MEMBER_UNION", ""),
    TOTAL_SUB_ORGANIZATION("TOTAL_SUB_ORGANIZATION", ""),
    TOTAL_TPS("TOTAL_TPS", ""),
    MEMBER_TODAY("MEMBER_TODAY", ""),
    MEMBER_YESTERDAY("MEMBER_YESTERDAY", ""),

    WORKSPACE_REGENCY_COUNT("WORKSPACE_REGENCY_COUNT", "Total Kabupaten"),
    WORKSPACE_DISTRICT_COUNT("WORKSPACE_DISTRICT_COUNT", "Total Kecamatan"),
    WORKSPACE_VILLAGE_COUNT("WORKSPACE_VILLAGE_COUNT", "Total Kelurahan"),
    WORKSPACE_VOTE_STATION_COUNT("WORKSPACE_VOTE_STATION_COUNT", "Total TPS"),

    /**
     * Statistic Today
     */
    VOTER_BASE_VOTER_COUNT_TODAY("VOTER_BASE_VOTER_COUNT_TODAY", ""),
    VOTER_BASE_CLUSTER_COUNT_TODAY("VOTER_BASE_CLUSTER_COUNT_TODAY", ""),
    VOTER_BASE_VOTER_COUNT_YESTERDAY("VOTER_BASE_VOTER_COUNT_YESTERDAY", ""),

    VOTER_BASE_CLUSTER_COUNT("VOTER_BASE_CLUSTER_COUNT", ""),
    VOTER_BASE_MEMBER_COUNT("VOTER_BASE_MEMBER_COUNT", ""),
    VOTER_BASE_VOTER_COUNT("VOTER_BASE_VOTER_COUNT", ""),
    VOTER_BASE_VOTE_STATION_COUNT("VOTER_BASE_VOTE_STATION_COUNT", ""),

    REAL_COUNT_VOTE_STATION_COUNT("REAL_COUNT_VOTE_STATION_COUNT", "Total TPS Tercover"),
    REAL_COUNT_FIXED_VOTER_COUNT("REAL_COUNT_FIXED_VOTER_COUNT", "Total DPT"),
    REAL_COUNT_VOTER_ALL_COUNT("REAL_COUNT_VOTER_ALL_COUNT", ""),
    REAL_COUNT_VOTER_VERIFIED_COUNT("REAL_COUNT_VOTER_VERIFIED_COUNT", ""),

    ;
    private final String code;
    private final String description;

    BizReportOverallKey(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static BizReportOverallKey getByCode(String code) {
        for (BizReportOverallKey overallKey : values()) {
            if (overallKey.code.equals(code)) {
                return overallKey;
            }
        }
        return null;
    }
}