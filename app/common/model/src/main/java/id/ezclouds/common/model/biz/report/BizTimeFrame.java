/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.report;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizTimeFrame.java, v 0.1 2024‐10‐25 9:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizTimeFrame {

    HOURLY("HOURLY"),
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    BIWEEKLY("BIWEEKLY"),
    MONTHLY("MONTHLY"),

    ;

    private final String code;

    BizTimeFrame(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizTimeFrame getByCode(String code) {
        for (BizTimeFrame timeFrame : values()) {
            if (timeFrame.code.equals(code)) {
                return timeFrame;
            }
        }
        return null;
    }
}