/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizUniqueScene.java, v 0.1 2024‐02‐27 9:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizUniqueScene {

    BIZ_SURVEY_RESPONSE("SURVEY0-"),

    ;

    private final String code;

    BizUniqueScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}