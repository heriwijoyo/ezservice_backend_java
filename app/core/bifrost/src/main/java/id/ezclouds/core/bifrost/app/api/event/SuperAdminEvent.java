/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.event;

import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SuperAdminEvent.java, v 0.1 2024‐03‐31 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum SuperAdminEvent implements EzAppEvent {

    SU_CREATE_WEB_SESSION("SU_CREATE_WEB_SESSION"),
    SU_CREATE_PUBLIC_SESSION("SU_CREATE_PUBLIC_SESSION"),

    ;

    private final String code;

    SuperAdminEvent(String code) {
        this.code = code;
    }

    @Override
    public String getEventCode() {
        return code;
    }
}