/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.migration;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MigrationScene.java, v 0.1 2024‐10‐05 12:54 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum MigrationScene {

    CORE_MEMBER_TO_BIZ_VOTER("CORE_MEMBER_TO_BIZ_VOTER"),

    ;

    private final String code;

    MigrationScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MigrationScene getByCode(String code) {
        for (MigrationScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return null;
    }
}