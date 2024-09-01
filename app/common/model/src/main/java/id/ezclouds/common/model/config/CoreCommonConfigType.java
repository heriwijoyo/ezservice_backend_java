/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.config;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreCommonConfigType.java, v 0.1 2024‐09‐01 11:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreCommonConfigType implements CoreConfigType {

    WATZAP_API_URI("WATZAP_API_URI"),

    ;

    private final String code;

    CoreCommonConfigType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public boolean isOrgSpecific() {
        return false;
    }
}