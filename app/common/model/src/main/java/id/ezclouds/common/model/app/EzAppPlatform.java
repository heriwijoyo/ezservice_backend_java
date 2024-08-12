/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.app;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAppPlatform.java, v 0.1 2024‐08‐12 8:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum EzAppPlatform {

    ANDROID_PHONE("ANDROID_PHONE"),
    ANDROID_TV("ANDROID_TV"),

    ;
    private final String code;

    EzAppPlatform(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}