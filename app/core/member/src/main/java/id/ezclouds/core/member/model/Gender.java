/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Gender.java, v 0.1 2023‐12‐31 9:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum Gender {

    MALE(0, "Laki - Laki"),
    FEMALE(1, "Perempuan"),
    ;

    private final int code;
    private final String label;

    Gender(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Gender getByCode(int code) {
        for (Gender gender : values()) {
            if (gender.getCode() == code) {
                return gender;
            }
        }
        return null;
    }

    public static Gender getByLabel(String label) {
        for (Gender gender : values()) {
            if (gender.getLabel().equals(label)) {
                return gender;
            }
        }
        return null;
    }
}