/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSmartTableQueryScenario.java, v 0.1 2024‐09‐15 4:51 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizSmartTableQueryScenario {

    REAL_COUNT_ALL("REAL_COUNT_ALL", "Total Data Masuk"),
    REAL_COUNT_ALL_VERIFIED("REAL_COUNT_ALL_VERIFIED", "Total Data Terverifikasi"),
    REAL_COUNT_REGENCY("REAL_COUNT_REGENCY", "Data Masuk Per Kabupaten"),
    REAL_COUNT_REGENCY_VERIFIED("REAL_COUNT_REGENCY_VERIFIED", "Data Terverifikasi Per Kabupaten"),

    ;

    private final String code;
    private final String title;

    BizSmartTableQueryScenario(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static BizSmartTableQueryScenario getByCode(String code) {
        for (BizSmartTableQueryScenario scenario : values()) {
            if (scenario.code.equals(code)) {
                return scenario;
            }
        }
        return null;
    }
}