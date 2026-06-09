/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSwitchFlagObject.java, v 0.1 2024‐04‐28 9:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum BizSwitchFlagObject {

    VIDEO_CARD("VIDEO_CARD"),

    UNKNOWN("UNKNOWN")
    ;
    private final String code;

    BizSwitchFlagObject(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizSwitchFlagObject getByCode(String code) {
        for (BizSwitchFlagObject flagObject : values()) {
            if (flagObject.getCode().equals(code)) {
                return flagObject;
            }
        }
        return UNKNOWN;
    }
}