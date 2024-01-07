/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizStatus.java, v 0.1 2024‐01‐07 4:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizStatus {

    ACTIVE(1),
    NOT_ACTIVE(0),
    DELETED(-1),
    UNKNOWN(-999),

    ;
    private final int code;

    BizStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
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