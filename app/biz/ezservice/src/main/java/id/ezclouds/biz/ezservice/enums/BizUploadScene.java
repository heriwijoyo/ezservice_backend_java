/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizUploadScene.java, v 0.1 2024‐02‐14 12:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizUploadScene {

    AVATAR("AVATAR"),
    ID_CARD("ID_CARD"),
    FAMILY_CARD("FAMILY_CARD"),
    REPORT_IMAGE("REPORT_IMAGE"),
    REPORT_VIDEO("REPORT_VIDEO"),
    REPORT_VOICE("REPORT_VOICE"),
    ADMIN_APP_BUILD_PACKAGE("ADMIN_APP_BUILD_PACKAGE"),
    ADMIN_APP_ICON("ADMIN_APP_ICON"),
    ADMIN_APP_GALLERY("ADMIN_APP_GALLERY"),
    ADMIN_NEWS_GALLERY("ADMIN_NEWS_GALLERY"),
    ADMIN_NEWS_GALLERY_UPDATE("ADMIN_NEWS_GALLERY_UPDATE"),
    ADMIN_EVENT_GALLERY("ADMIN_EVENT_GALLERY"),
    ADMIN_EVENT_GALLERY_UPDATE("ADMIN_EVENT_GALLERY_UPDATE"),
    ADMIN_VIDEO_CARD_GALLERY("ADMIN_VIDEO_CARD_GALLERY"),
    ADMIN_DOCS_GALLERY("ADMIN_DOCS_GALLERY"),

    UNKNOWN("UNKNOWN"),
    ;

    private final String code;

    BizUploadScene(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BizUploadScene getByCode(String code) {
        for (BizUploadScene scene : values()) {
            if (scene.getCode().equals(code)) {
                return scene;
            }
        }
        return UNKNOWN;
    }
}