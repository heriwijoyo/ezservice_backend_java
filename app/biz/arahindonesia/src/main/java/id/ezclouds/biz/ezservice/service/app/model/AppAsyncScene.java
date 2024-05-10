/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppAsyncScene.java, v 0.1 2024‐05‐10 10:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AppAsyncScene {

    RJL_COMMON_SURVEY("RJL_COMMON_SURVEY"),
    UNKNOWN("UNKNOWN")
    ;

    private final String code;

    AppAsyncScene(String code) {
        this.code = code;
    }

    public static AppAsyncScene getByCode(String code) {
        for (AppAsyncScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return UNKNOWN;
    }
}