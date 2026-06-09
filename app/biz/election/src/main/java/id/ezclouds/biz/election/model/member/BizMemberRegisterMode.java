/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.member;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRegisterMode.java, v 0.1 2024‐05‐23 6:39 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizMemberRegisterMode {

    SELF_PUBLIC("SELF_PUBLIC"),
    SELF_VOLUNTEER("SELF_VOLUNTEER"),
    BY_ORG_ADMIN("BY_ORG_ADMIN"),
    BY_SUB_ORG_ADMIN("BY_SUB_ORG_ADMIN"),
    BY_RECRUITER("BY_RECRUITER"),
    UNKNOWN("UNKNOWN"),
    ;

    private final String code;

    BizMemberRegisterMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizMemberRegisterMode getByCode(String code) {
        for (BizMemberRegisterMode mode : values()) {
            if (mode.code.equals(code)) {
                return mode;
            }
        }
        return UNKNOWN;
    }
}