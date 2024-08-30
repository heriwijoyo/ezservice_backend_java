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

    MEMBER_PHONE("MEMBER_PHONE", "Search By Phone"),
    MEMBER_NAME_CONTAIN("MEMBER_NAME_CONTAIN", "Search By Name"),
    MEMBER_REFERRER("MEMBER_REFERRER", "Search By Recruiter"),
    CODE("CODE", "Search By Code"),
    UNKNOWN("UNKNOWN", "Unknown"),
    ;

    private final String code;
    private final String label;

    SearchScene(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
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