/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageAuthType.java, v 0.1 2024‐08‐22 8:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebPageAuthType {

    PUBLIC_SESSION("PUBLIC_SESSION"),
    UNKNOWN("UNKNOWN"),
    ;

    private final String code;

    WebPageAuthType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static WebPageAuthType getByCode(String code) {
        for (WebPageAuthType authType : values()) {
            if (authType.code.equals(code)) {
                return authType;
            }
        }
        return UNKNOWN;
    }
}