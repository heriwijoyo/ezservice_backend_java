/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthRole.java, v 0.1 2024‐08‐17 8:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AuthRole {

    SUPERUSER("SUPERUSER"),
    ADMIN_ORG("ADMIN_ORG"),
    ADMIN_SUB_ORG("ADMIN_SUB_ORG"),
    OP_RECRUITER("OP_RECRUITER"),
    OP_SURVEYOR("OP_SURVEYOR"),

    UNKNOWN("UNKNOWN"),
    ;
    private final String code;

    AuthRole(String code) {
        this.code = code;
    }

    public static AuthRole getByCode(String code) {
        for (AuthRole authRole : values()) {
            if (authRole.code.equals(code)) {
                return authRole;
            }
        }
        return UNKNOWN;
    }
}