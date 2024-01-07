/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreUniqueScene.java, v 0.1 2024‐01‐07 8:08 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreUniqueScene {

    CORE_MEMBER_ID("CORE_MEMBER_ID")
    ;
    private final String code;

    CoreUniqueScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}