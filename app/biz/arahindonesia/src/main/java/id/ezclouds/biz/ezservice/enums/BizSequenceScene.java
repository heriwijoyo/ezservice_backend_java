/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSequenceScene.java, v 0.1 2024‐02‐27 9:17 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum  BizSequenceScene {

    BIZ_SURVEY_RESPONSE("BIZ_SURVEY_RESPONSE"),

    ;

    private final String code;

    BizSequenceScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}