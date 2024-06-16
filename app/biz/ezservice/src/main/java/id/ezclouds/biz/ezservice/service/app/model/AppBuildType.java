/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppBuildType.java, v 0.1 2024‐06‐16 12:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AppBuildType {

    ANDROID("ANDROID"),
    IOS("IOS"),
    WEB("WEB"),
    UNKNOWN("UNKNOWN")

    ;
    private final String code;

    AppBuildType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}