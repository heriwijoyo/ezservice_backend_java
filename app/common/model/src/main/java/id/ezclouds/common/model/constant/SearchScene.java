/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.constant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SearchScene.java, v 0.1 2024‐08‐11 12:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum SearchScene {

    MEMBER_PHONE("MEMBER_PHONE"),
    MEMBER_NAME_CONTAIN("MEMBER_NAME_CONTAIN"),
    UNKNOWN("UNKNOWN"),
    ;

    private final String code;

    SearchScene(String code) {
        this.code = code;
    }

    public static SearchScene getByCode(String code) {
        for (SearchScene searchScene : values()) {
            if (searchScene.code.equals(code)) {
                return searchScene;
            }
        }
        return UNKNOWN;
    }
}