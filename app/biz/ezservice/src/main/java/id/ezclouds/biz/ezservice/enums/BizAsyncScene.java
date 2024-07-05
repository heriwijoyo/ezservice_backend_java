/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncScene.java, v 0.1 2024‐07‐06 1:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizAsyncScene {

    MEMBER_REGISTER("MEMBER_REGISTER"),
    MEMBER_DATA_IMPORT_24JULY("MEMBER_DATA_IMPORT_24JULY"),

    ;

    private final String code;

    BizAsyncScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}