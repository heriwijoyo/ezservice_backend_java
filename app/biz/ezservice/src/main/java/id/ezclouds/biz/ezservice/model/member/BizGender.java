/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.member;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizGender.java, v 0.1 2024‐01‐01 10:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum BizGender {

    MALE("MALE", "Laki - Laki"),
    FEMALE("FEMALE", "Perempuan"),
    UNKNOWN("UNKNOWN", "-"),
    ;

    private final String code;
    private final String label;

    BizGender(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static BizGender getByCode(String code) {
        for (BizGender bizGender : values()) {
            if (bizGender.getCode().equals(code)) {
                return bizGender;
            }
        }
        return UNKNOWN;
    }
}