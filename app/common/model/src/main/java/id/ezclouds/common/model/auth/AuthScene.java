/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.auth;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthScene.java, v 0.1 2024‐08‐24 11:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum AuthScene {

    WEB_LOGIN_SESSION("WEB_LOGIN_SESSION"),
    WEB_PUBLIC_SESSION("WEB_PUBLIC_SESSION"),

    ;
    private final String code;

    AuthScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AuthScene getByCode(String code) {
        for (AuthScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return null;
    }
}