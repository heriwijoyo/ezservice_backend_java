/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizValidationScene.java, v 0.1 2024‐09‐30 11:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizValidationScene {

    BIZ_VOTER_REGISTER("BIZ_VOTER_REGISTER"),

    ;

    private final String code;

    BizValidationScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizValidationScene getByCode(String code) {
        for (BizValidationScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return null;
    }
}