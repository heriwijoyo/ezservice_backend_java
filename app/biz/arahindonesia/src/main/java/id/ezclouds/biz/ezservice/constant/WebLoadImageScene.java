/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.constant;

import id.ezclouds.common.util.StringUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebLoadImageScene.java, v 0.1 2024‐02‐09 2:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebLoadImageScene {

    AVATAR("AVATAR"),
    ID_CARD("ID_CARD"),
    FAMILY_CARD("FAMILY_CARD"),

    PUBLIC_SLIDE("PUBLIC_SLIDE"),
    PUBLIC_NEWS("PUBLIC_NEWS"),
    PUBLIC_EVENT("PUBLIC_EVENT"),
    PUBLIC_REPORT_IMAGE("PUBLIC_REPORT_IMAGE"),
    PUBLIC_REPORT_VIDEO("PUBLIC_REPORT_VIDEO"),
    PUBLIC_REPORT_VOICE("PUBLIC_REPORT_VOICE"),

    ;

    private final String code;

    WebLoadImageScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static WebLoadImageScene getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (WebLoadImageScene scene : values()) {
            if (scene.code.equals(code)) {
                return scene;
            }
        }
        return null;
    }
}