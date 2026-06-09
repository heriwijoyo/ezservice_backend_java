/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataScene.java, v 0.1 2024‐09‐19 3:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizMasterDataScene {

    OVERALL_STATIC("OVERALL_STATIC"),
    AREA_VILLAGE_STATIC("AREA_VILLAGE_STATIC"),

    ;

    private final String code;

    BizMasterDataScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}