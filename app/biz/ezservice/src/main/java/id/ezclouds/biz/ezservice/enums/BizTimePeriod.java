/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizTimePeriod.java, v 0.1 2024‐07‐17 1:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizTimePeriod {

    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    ;

    private final String code;

    BizTimePeriod(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}