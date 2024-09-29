/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.event;

import id.ezclouds.common.util.context.EzAppEvent;
import id.ezclouds.common.util.logger.CommonLoggerConstant;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiEvent.java, v 0.1 2023‐12‐09 2:04 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public enum ApiEvent implements EzAppEvent {

    API_APP_SETTING("API_APP_SETTING", CommonLoggerConstant.API_CONTROLLER),
    API_SURVEY_FORM("API_SURVEY_FORM", CommonLoggerConstant.API_CONTROLLER),
    API_CANDIDATE_PROFILE("API_CANDIDATE_PROFILE", CommonLoggerConstant.API_CONTROLLER),
    API_NEWS("API_NEWS", CommonLoggerConstant.API_CONTROLLER),
    API_NEWS_DETAIL("API_NEWS_DETAIL", CommonLoggerConstant.API_CONTROLLER),
    API_APP_EVENT("API_APP_EVENT", CommonLoggerConstant.API_CONTROLLER),
    API_MESSAGE_MEMBER("API_MESSAGE_MEMBER", CommonLoggerConstant.API_CONTROLLER),
    API_MESSAGE_MEMBER_DETAIL("API_MESSAGE_MEMBER_DETAIL", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_PROFILE("API_MEMBER_PROFILE", CommonLoggerConstant.API_CONTROLLER),
    API_GET_LOCAL_AREA("API_GET_LOCAL_AREA", CommonLoggerConstant.API_CONTROLLER),
    API_GET_SUB_ORGANIZATIONS("API_GET_SUB_ORGANIZATIONS", CommonLoggerConstant.API_CONTROLLER),

    // PageResult APIs
    API_PAGE_SUB_ORGANIZATIONS("API_GET_SUB_ORGANIZATIONS", CommonLoggerConstant.API_CONTROLLER),
    API_PAGE_MEMBER("API_GET_MEMBER", CommonLoggerConstant.API_CONTROLLER),
    API_PAGE_APP_DOCUMENTS("API_GET_APP_DOCUMENTS", CommonLoggerConstant.API_CONTROLLER),

    // TRANSACTIONAL APIs
    API_MEMBER_LOGIN("API_MEMBER_LOGIN", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_LOGOUT("API_MEMBER_LOGOUT", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_REGISTER("API_MEMBER_REGISTER", CommonLoggerConstant.API_CONTROLLER),
    API_SESSION_CHECK("API_SESSION_CHECK", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_UPDATE_PASSWORD("API_MEMBER_UPDATE_PASSWORD", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_RESET_PASSWORD("API_MEMBER_RESET_PASSWORD", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_VERIFY_COMMON_SESSION("API_MEMBER_VERIFY_COMMON_SESSION", CommonLoggerConstant.API_CONTROLLER),
    API_MEMBER_UPLOAD_MEDIA("API_MEMBER_UPLOAD_MEDIA", CommonLoggerConstant.API_CONTROLLER),
    API_SURVEY_SUBMIT("API_SURVEY_SUBMIT", CommonLoggerConstant.API_CONTROLLER),
    API_SUB_ORG_CREATE("API_SUB_ORG_CREATE", CommonLoggerConstant.API_CONTROLLER),

    API_VOTER_REGISTER("API_VOTER_REGISTER", CommonLoggerConstant.API_CONTROLLER_ELECTION),

    API_ASYNC_PROCESS_TRIGGER("API_ASYNC_PROCESS_TRIGGER", CommonLoggerConstant.API_CONTROLLER),

    API_ADMIN_CREATE_WEB_SESSION("API_ADMIN_CREATE_WEB_SESSION", CommonLoggerConstant.API_CONTROLLER),
    API_ADMIN_GET_WEB_SESSION("API_ADMIN_GET_WEB_SESSION", CommonLoggerConstant.API_CONTROLLER),
    API_ADMIN_LOGOUT_WEB_SESSION("API_ADMIN_LOGOUT_WEB_SESSION", CommonLoggerConstant.API_CONTROLLER),
    API_ADMIN_UPLOAD_MEDIA("API_ADMIN_UPLOAD_MEDIA", CommonLoggerConstant.API_CONTROLLER),
    API_ADMIN_MEMBER_UPDATE("API_ADMIN_MEMBER_UPDATE", CommonLoggerConstant.API_CONTROLLER),
    ;

    private final String code;
    private final String logger;

    ApiEvent(String code, String logger) {
        this.code = code;
        this.logger = logger;
    }

    @Override
    public String getEventCode() {
        return code;
    }

    public String getLogger() {
        return logger;
    }
}