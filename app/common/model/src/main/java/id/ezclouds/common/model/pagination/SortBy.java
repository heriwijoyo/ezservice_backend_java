/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.pagination;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SortBy.java, v 0.1 2024‐10‐05 1:10 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum SortBy {

    NEWEST("NEWEST"),
    OLDEST("OLDEST"),
    NUMBER_HIGHEST("NUMBER_HIGHEST"),
    NUMBER_LOWEST("NUMBER_LOWEST"),

    UNKNOWN("UNKNOWN")
    ;

    private final String code;

    SortBy(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SortBy getByCode(String code) {
        for (SortBy sortBy : values()) {
            if (sortBy.code.equals(code)) {
                return sortBy;
            }
        }
        return UNKNOWN;
    }
}