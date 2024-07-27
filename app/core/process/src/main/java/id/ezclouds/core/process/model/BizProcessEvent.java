/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessEvent.java, v 0.1 2024‐07‐28 4:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizProcessEvent {

    GENERATE_REPORT_OVERALL("GENERATE_REPORT_OVERALL"),
    UNKNOWN("UNKNOWN"),
    ;
    private final String code;

    BizProcessEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}