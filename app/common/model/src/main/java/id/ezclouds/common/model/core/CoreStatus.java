/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreStatus.java, v 0.1 2024‐10‐05 1:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreStatus {

    ACTIVE(1, "ACTIVE"),
    NOT_ACTIVE(0, "NOT_ACTIVE"),
    DELETED(-1, "DELETED"),

    ;

    private final int code;
    private final String description;

    CoreStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CoreStatus getByStatus(int status) {
        for (CoreStatus coreStatus : values()) {
            if (coreStatus.code == status) {
                return coreStatus;
            }
        }
        return null;
    }
}