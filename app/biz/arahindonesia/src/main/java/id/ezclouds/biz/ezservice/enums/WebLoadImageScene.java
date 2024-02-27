/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.enums;

import id.ezclouds.common.util.StringUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebLoadImageScene.java, v 0.1 2024‐02‐09 2:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebLoadImageScene {

    AVATAR("avatar", ImageRestriction.PRIVATE),
    ID_CARD("idcard", ImageRestriction.PRIVATE),
    FAMILY_CARD("famcard", ImageRestriction.PRIVATE),

    PUBLIC_APP_GALLERY("app", ImageRestriction.PUBLIC),
    PUBLIC_NEWS_GALLERY("news", ImageRestriction.PUBLIC),
    PUBLIC_EVENT_GALLERY("event", ImageRestriction.PUBLIC),

    PUBLIC_REPORT_IMAGE("PUBLIC_REPORT_IMAGE", ImageRestriction.PRIVATE),
    PUBLIC_REPORT_VIDEO("PUBLIC_REPORT_VIDEO", ImageRestriction.PRIVATE),
    PUBLIC_REPORT_VOICE("PUBLIC_REPORT_VOICE", ImageRestriction.PRIVATE),

    UNKNOWN("UNKNOWN", ImageRestriction.PUBLIC),
    ;

    private final String code;
    private final ImageRestriction restriction;

    WebLoadImageScene(String code, ImageRestriction restriction) {
        this.code = code;
        this.restriction = restriction;
    }

    public String getCode() {
        return code;
    }

    public ImageRestriction getRestriction() {
        return restriction;
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
        return UNKNOWN;
    }
}