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

    API_APP_SETTING("API_APP_SETTING", "ApiAppSetting"),
    API_CANDIDATE_PROFILE("API_CANDIDATE_PROFILE", "CandidateProfile"),
    API_NEWS("API_NEWS", "News"),
    API_MEMBER_PROFILE("API_MEMBER_PROFILE", "MemberProfile"),

    // TRANSACTIONAL APIs
    API_MEMBER_LOGIN("API_MEMBER_LOGIN", "MemberLogin"),
    API_MEMBER_LOGOUT("API_MEMBER_LOGOUT", "MemberLogout"),
    API_MEMBER_REGISTER("API_MEMBER_REGISTER", "MemberRegister"),
    API_SESSION_CHECK("API_SESSION_CHECK", "SessionCheck"),
    API_MEMBER_UPDATE_PASSWORD("API_MEMBER_UPDATE_PASSWORD", "MemberUpdatePassword"),
    GENERATE_SEQUENCE("", "GenerateSequence"),
    UNKNOWN_EVENT("UNKNOWN_EVENT", "Undefined event"),

    // SAMPLE
    SAMPLE_EVENT("SAMPLE_EVENT", "Sample event"),
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