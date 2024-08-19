/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.process;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessMode.java, v 0.1 2024‐08‐19 8:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum ProcessMode {

    SYNC("SYNC"),
    ASYNC("ASYNC"),
    UNKNOWN("UNKNOWN"),

    ;
    private final String code;

    ProcessMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ProcessMode getByCode(String code) {
        for (ProcessMode mode : values()) {
            if (mode.code.equals(code)) {
                return mode;
            }
        }
        return UNKNOWN;
    }
}