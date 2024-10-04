/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.member;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreGender.java, v 0.1 2024‐10‐05 1:45 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreGender {

    MALE("MALE"),
    FEMALE("FEMALE"),

    ;

    private final String code;

    CoreGender(String code) {
        this.code = code;
    }

    public static CoreGender getByCode(String code) {
        for (CoreGender coreGender : values()) {
            if (coreGender.code.equals(code)) {
                return coreGender;
            }
        }
        return null;
    }
}