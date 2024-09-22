/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRole.java, v 0.1 2024‐02‐14 1:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum  BizMemberRole {

    SUPERUSER("SUPERUSER"),
    ADMIN_ORG("ADMIN_ORG"),
    ADMIN_SUB_ORG("ADMIN_SUB_ORG"),

    BIZ_CLIENT("BIZ_CLIENT"),

    OP_SURVEYOR("OP_SURVEYOR"),
    OP_RECRUITER("OP_RECRUITER"),

    ;
    private final String code;

    BizMemberRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizMemberRole getByCode(String code) {
        for (BizMemberRole role : values()) {
            if (role.getCode().equals(code)) {
                return role;
            }
        }
        return null;
    }
}