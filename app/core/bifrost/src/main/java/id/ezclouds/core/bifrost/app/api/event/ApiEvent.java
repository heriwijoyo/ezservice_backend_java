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

    API_APP_SETTING("API_APP_SETTING"),
    API_SURVEY_FORM("API_SURVEY_FORM"),
    API_CANDIDATE_PROFILE("API_CANDIDATE_PROFILE"),
    API_NEWS("API_NEWS"),
    API_MEMBER_PROFILE("API_MEMBER_PROFILE"),
    API_GET_LOCAL_AREA("API_GET_LOCAL_AREA"),

    // TRANSACTIONAL APIs
    API_MEMBER_LOGIN("API_MEMBER_LOGIN"),
    API_MEMBER_LOGOUT("API_MEMBER_LOGOUT"),
    API_MEMBER_REGISTER("API_MEMBER_REGISTER"),
    API_SESSION_CHECK("API_SESSION_CHECK"),
    API_MEMBER_UPDATE_PASSWORD("API_MEMBER_UPDATE_PASSWORD"),
    API_MEMBER_RESET_PASSWORD("API_MEMBER_RESET_PASSWORD"),
    API_MEMBER_VERIFY_COMMON_SESSION("API_MEMBER_VERIFY_COMMON_SESSION"),
    API_MEMBER_UPLOAD_MEDIA("API_MEMBER_UPLOAD_MEDIA"),
    API_SURVEY_SUBMIT("API_SURVEY_SUBMIT"),

    API_ADMIN_CREATE_WEB_SESSION("API_ADMIN_CREATE_WEB_SESSION"),
    API_ADMIN_GET_WEB_SESSION("API_ADMIN_GET_WEB_SESSION"),
    API_ADMIN_LOGOUT_WEB_SESSION("API_ADMIN_LOGOUT_WEB_SESSION"),
    API_ADMIN_UPLOAD_MEDIA("API_ADMIN_UPLOAD_MEDIA"),
    ;

    private final String code;

    ApiEvent(String code) {
        this.code = code;
    }

    @Override
    public String getEventCode() {
        return code;
    }
}