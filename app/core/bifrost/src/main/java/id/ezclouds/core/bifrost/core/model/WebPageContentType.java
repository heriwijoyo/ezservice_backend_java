/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageContentType.java, v 0.1 2024‐08‐22 8:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum  WebPageContentType {

    COMMON_TABLE("COMMON_TABLE"),
    UNKNOWN("UNKNOWN"),
    ;

    private final String code;

    WebPageContentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static WebPageContentType getByCode(String code) {
        for (WebPageContentType contentType : values()) {
            if (contentType.code.equals(code)) {
                return contentType;
            }
        }
        return UNKNOWN;
    }
}