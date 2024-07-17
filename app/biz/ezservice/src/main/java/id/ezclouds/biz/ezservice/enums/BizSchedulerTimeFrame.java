/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSchedulerTimeFrame.java, v 0.1 2024‐07‐18 12:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizSchedulerTimeFrame {

    MINUTE("MINUTE"),
    CUSTOM_DAILY_REPORT("CUSTOM_DAILY_REPORT"),

    ;

    private final String code;

    BizSchedulerTimeFrame(String code) {
        this.code = code;
    }
}