/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.process;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ProcessStatus.java, v 0.1 2024‐10‐02 2:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum ProcessStatus {

    INIT("INIT"),
    SUCCESS("SUCCESS"),
    EXCEPTION("EXCEPTION"),

    ;

    private final String code;

    ProcessStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ProcessStatus getByCode(String code) {
        for (ProcessStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}