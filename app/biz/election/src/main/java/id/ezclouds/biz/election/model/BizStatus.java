/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizStatus.java, v 0.1 2024‐01‐07 4:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizStatus {

    ACTIVE(1, "ACTIVE"),
    NOT_ACTIVE(0, "NOT_ACTIVE"),
    DELETED(-1, "DELETED"),
    UNKNOWN(-999, "UNKNOWN"),

    ;
    private final int code;
    private final String description;

    BizStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static BizStatus getByCode(int code) {
        for (BizStatus bizStatus : values()) {
            if (bizStatus.getCode() == code) {
                return bizStatus;
            }
        }
        return BizStatus.UNKNOWN;
    }
}