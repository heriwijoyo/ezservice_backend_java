/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.app;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSystemSource.java, v 0.1 2024‐09‐29 10:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AppSystemSource {

    APP("APP"),
    WEB("WEB"),
    WEB_IMPORT("WEB_IMPORT"),

    ;

    private final String code;

    AppSystemSource(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}