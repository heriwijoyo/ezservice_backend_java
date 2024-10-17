/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataOverall.java, v 0.1 2024‐09‐21 11:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum  BizMasterDataOverall {

    WORKSPACE_REGENCY_COUNT("WORKSPACE_REGENCY_COUNT", "Total Kabupaten"),
    WORKSPACE_DISTRICT_COUNT("WORKSPACE_DISTRICT_COUNT", "Total Kecamatan"),
    WORKSPACE_VILLAGE_COUNT("WORKSPACE_VILLAGE_COUNT", "Total Kelurahan"),
    WORKSPACE_VOTE_STATION_COUNT("WORKSPACE_VOTE_STATION_COUNT", "Total TPS"),

    ;

    private final String code;
    private final String description;

    BizMasterDataOverall(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static BizMasterDataOverall getByCode(String code) {
        for (BizMasterDataOverall overall : values()) {
            if (overall.code.equals(code)) {
                return overall;
            }
        }
        return null;
    }
}