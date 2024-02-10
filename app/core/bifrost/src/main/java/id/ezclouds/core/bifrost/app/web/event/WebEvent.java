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

    GET_IMAGE_AVATAR("GET_IMAGE_AVATAR"),
    GET_IMAGE_IDCARD("GET_IMAGE_IDCARD"),
    GET_IMAGE_FAMCARD("GET_IMAGE_FAMCARD"),

    WEB_LOGIN_BY_SESSION_CODE("WEB_LOGIN_BY_SESSION_CODE"),

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