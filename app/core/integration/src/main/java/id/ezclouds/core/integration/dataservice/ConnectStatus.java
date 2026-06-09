/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.dataservice;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ConnectStatus.java, v 0.1 2024‐05‐16 4:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum ConnectStatus {

    SENDING("SENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),

    ;
    private final String code;

    ConnectStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}