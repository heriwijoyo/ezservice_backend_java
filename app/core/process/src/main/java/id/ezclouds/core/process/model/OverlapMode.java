/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: OverlapMode.java, v 0.1 2024‐10‐05 3:39 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum OverlapMode {

    PARALLEL("PARALLEL"),
    SERIAL("SERIAL"),
    ONLY_SINGLE("ONLY_SINGLE"),

    ;

    private final String code;

    OverlapMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}