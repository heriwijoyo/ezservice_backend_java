/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthMemberClientLoginType.java, v 0.1 2024‐08‐14 5:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AuthMemberClientLoginType {

    PHONE("PHONE"),

    ;

    private final String code;

    AuthMemberClientLoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}