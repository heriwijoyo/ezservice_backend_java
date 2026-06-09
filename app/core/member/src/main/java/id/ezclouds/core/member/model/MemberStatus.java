/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberStatus.java, v 0.1 2023‐12‐31 9:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum MemberStatus {

    ACTIVE(1),
    NOT_ACTIVE(0),
    DELETED(-1),
    UNKNOWN(-999),

    ;

    private final int code;

    MemberStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MemberStatus getByCode(int code) {
        for (MemberStatus memberStatus : values()) {
            if (memberStatus.getCode() == code) {
                return memberStatus;
            }
        }
        return MemberStatus.UNKNOWN;
    }
}