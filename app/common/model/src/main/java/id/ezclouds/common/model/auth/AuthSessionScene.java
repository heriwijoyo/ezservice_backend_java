/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthSessionScene.java, v 0.1 2024‐08‐24 11:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AuthSessionScene {

    WEB_PUBLIC_SESSION("WEB_PUBLIC_SESSION"),

    ;
    private final String code;

    AuthSessionScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AuthSessionScene getByCode(String code) {
        for (AuthSessionScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return null;
    }
}