/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizImportScene.java, v 0.1 2024‐07‐04 7:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizImportScene {

    MEMBER_REGISTER_2024_JULY_EARLY("MEMBER_REGISTER_2024_JULY_EARLY"),

    UNKNOWN("UNKNOWN")
    ;

    private final String code;

    BizImportScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizImportScene getByCode(String code) {
        for (BizImportScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return UNKNOWN;
    }
}