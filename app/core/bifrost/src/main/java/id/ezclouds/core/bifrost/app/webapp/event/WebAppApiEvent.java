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

    WEBAPP_API_MASTER_DATA_OVERALL("WEBAPP_API_MASTER_DATA_OVERALL"),
    WEBAPP_API_MASTER_DATA_OVERALL_UPDATE("WEBAPP_API_MASTER_DATA_OVERALL_UPDATE"),
    WEBAPP_API_AREA_DISTRICTS("WEBAPP_API_AREA_DISTRICTS"),
    WEBAPP_API_MASTER_DATA_VILLAGE("WEBAPP_API_MASTER_DATA_VILLAGE"),
    WEBAPP_API_MASTER_DATA_VILLAGE_UPDATE("WEBAPP_API_MASTER_DATA_VILLAGE_UPDATE"),

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