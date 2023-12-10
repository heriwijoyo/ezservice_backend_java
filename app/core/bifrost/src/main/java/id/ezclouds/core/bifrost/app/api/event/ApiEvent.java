/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.event;

import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiEvent.java, v 0.1 2023‐12‐09 2:04 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum ApiEvent implements EzAppEvent {

    API_APP_SETTING("001", "ApiAppSetting"),
    CANDIDATE_PROFILE("002", "CandidateProfile"),
    NEWS("003", "News"),
    MEMBER_PROFILE("004", "MemberProfile"),

    ;

    private String code;
    private String description;

    ApiEvent(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getEventCode() {
        return code;
    }
}