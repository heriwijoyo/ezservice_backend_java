/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.biz;

import id.ezclouds.common.util.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportWebApiEvent.java, v 0.1 2024‐10‐30 8:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum BizReportWebApiEvent implements EzAppEvent {

    BIZ_REPORT_WEB_API_GET_SUB_ORGANIZATIONS("BIZ_REPORT_WEB_API_GET_SUB_ORGANIZATIONS"),
    BIZ_REPORT_WEB_API_GET_SURVEY_RECAP("BIZ_REPORT_WEB_API_GET_SURVEY_RECAP"),

    ;

    private final String code;

    BizReportWebApiEvent(String code) {
        this.code = code;
    }

    @Override
    public String getEventCode() {
        return null;
    }
}