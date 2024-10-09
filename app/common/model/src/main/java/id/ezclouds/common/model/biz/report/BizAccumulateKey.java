/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAccumulateKey.java, v 0.1 2024‐10‐03 1:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizAccumulateKey {

    RELIGION("RELIGION"),
    EDUCATION("EDUCATION"),
    OCCUPATION("OCCUPATION"),
    ETHNIC("ETHNIC"),
    POLL_STATION_ID("POLL_STATION_ID"),
    NEIGHBOURHOOD("NEIGHBOURHOOD"),
    SUB_NEIGHBOURHOOD("SUB_NEIGHBOURHOOD"),

    ;

    private final String code;

    BizAccumulateKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizAccumulateKey getByCode(String code) {
        for (BizAccumulateKey accumulateKey : values()) {
            if (accumulateKey.code.equals(code)) {
                return accumulateKey;
            }
        }
        return null;
    }
}