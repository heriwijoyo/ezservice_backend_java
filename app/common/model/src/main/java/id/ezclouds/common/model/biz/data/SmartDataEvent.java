/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SmartDataEvent.java, v 0.1 2024‐09‐05 11:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum SmartDataEvent {

    DATA_QUERY("DATA_QUERY"),
    DATA_STORE("DATA_STORE"),
    UNKNOWN("UNKNOWN"),

    ;

    private final String code;

    SmartDataEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SmartDataEvent getByCode(String code) {
        for (SmartDataEvent event : values()) {
            if (event.code.equals(code)) {
                return event;
            }
        }
        return UNKNOWN;
    }
}