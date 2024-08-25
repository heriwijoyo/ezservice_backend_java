/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageSection.java, v 0.1 2024‐08‐22 8:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebPageSection {

    AREA("area"),
    UNKNOWN("UNKNOWN_SECTION"),

    ;
    private final String code;

    WebPageSection(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static WebPageSection getByCode(String code) {
        for (WebPageSection section : values()) {
            if (section.code.equals(code)) {
                return section;
            }
        }
        return UNKNOWN;
    }
}