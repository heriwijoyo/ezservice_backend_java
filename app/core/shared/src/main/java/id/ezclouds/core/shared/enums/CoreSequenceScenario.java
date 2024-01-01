/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceScenario.java, v 0.1 2024‐01‐01 2:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreSequenceScenario {

    CORE_MEMBER_ID("CORE_MEMBER_ID"),

    ;

    private final String code;

    CoreSequenceScenario(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}