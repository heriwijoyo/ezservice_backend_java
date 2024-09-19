/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp.event;

import id.ezclouds.common.util.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppApiEvent.java, v 0.1 2024‐09‐20 12:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebAppApiEvent implements EzAppEvent {

    WEB_APP_API_AREA_DISTRICTS("WEB_APP_API_AREA_DISTRICTS"),

    ;

    private final String code;

    WebAppApiEvent(String code) {
        this.code = code;
    }

    @Override
    public String getEventCode() {
        return code;
    }
}