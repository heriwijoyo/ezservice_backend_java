/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.feature;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: FeatureSection.java, v 0.1 2024‐10‐13 5:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum FeatureSection {

    CORE("CORE"),
    BIZ_ELECTION("BIZ_ELECTION"),
    BIZ_COMMERCE("BIZ_COMMERCE"),

    ;

    private final String code;

    FeatureSection(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static FeatureSection getByCode(String code) {
        for (FeatureSection section : values()) {
            if (section.code.equals(code)) {
                return section;
            }
        }
        return null;
    }
}