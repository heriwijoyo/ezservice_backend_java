/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: FeatureSwitchMode.java, v 0.1 2024‐09‐19 12:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum FeatureSwitchMode {

    OPEN("OPEN"),
    CLOSE("CLOSE"),
    GREY("GREY"),

    ;

    private final String code;

    FeatureSwitchMode(String code) {
        this.code = code;
    }

    public static FeatureSwitchMode getByCode(String code) {
        for (FeatureSwitchMode mode : values()) {
            if (mode.code.equals(code)) {
                return mode;
            }
        }
        return CLOSE;
    }
}