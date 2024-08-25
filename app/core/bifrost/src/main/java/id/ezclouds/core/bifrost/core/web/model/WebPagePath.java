/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPagePath.java, v 0.1 2024‐08‐22 8:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebPagePath {

    REPORT("report"),
    UNKNOWN("UNKNOWN_PATH"),

    ;
    private final String code;

    WebPagePath(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static WebPagePath getByCode(String code) {
        for (WebPagePath pagePath : values()) {
            if (pagePath.code.equals(code)) {
                return pagePath;
            }
        }
        return UNKNOWN;
    }
}