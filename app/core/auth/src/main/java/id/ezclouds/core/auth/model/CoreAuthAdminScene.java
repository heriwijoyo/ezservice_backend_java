/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthAdminScene.java, v 0.1 2024‐07‐13 5:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreAuthAdminScene {

    WEB_LOGIN_SESSION("WEB_LOGIN_SESSION"),
    WEB_PUBLIC_SESSION("WEB_PUBLIC_SESSION"),

    ;

    private final String code;

    CoreAuthAdminScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}