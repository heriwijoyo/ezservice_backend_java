/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProfileSection.java, v 0.1 2024‐05‐01 10:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizProfileSection {

    VISION("VISION"),
    MISSION("MISSION"),
    CONTACT_NUMBER("CONTACT_NUMBER"),

    UNKNOWN("UNKNOWN");

    private final String code;

    BizProfileSection(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizProfileSection getByCode(String code) {
        for (BizProfileSection section : values()) {
            if (section.getCode().equals(code)) {
                return section;
            }
        }
        return UNKNOWN;
    }
}