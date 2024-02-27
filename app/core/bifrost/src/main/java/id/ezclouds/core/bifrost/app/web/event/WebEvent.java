/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.event;

import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebEvent.java, v 0.1 2024‐02‐04 11:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebEvent implements EzAppEvent {

    GET_IMAGE_PUBLIC("GET_IMAGE_PUBLIC"),
    GET_IMAGE_PRIVATE("GET_IMAGE_PRIVATE"),

    WEB_LOGIN_BY_SESSION_CODE("WEB_LOGIN_BY_SESSION_CODE"),
    WEB_VALIDATE_SESSION_ID("WEB_VALIDATE_SESSION_ID"),
    WEB_API_GET_APP_DATA("WEB_API_GET_APP_DATA"),
    WEB_API_GET_DASHBOARD("WEB_API_GET_DASHBOARD"),

    APP_IMAGE_GALLERY("APP_IMAGE_GALLERY"),
    ADMIN_COMMON_POST_WITH_FILE_UPLOAD("ADMIN_COMMON_POST_WITH_FILE_UPLOAD"),

    WEB_API_REFRESH_ALL_CACHES("WEB_API_REFRESH_ALL_CACHES"),

    ;

    private final String code;

    WebEvent(String code) {
        this.code = code;
    }

    @Override
    public String getEventCode() {
        return this.code;
    }
}