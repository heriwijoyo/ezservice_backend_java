/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.web.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPageContentType.java, v 0.1 2024‐08‐22 8:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum  WebPageContentType {

    COMMON_TABLES("COMMON_TABLES", "pages/layout/commonTables.htm"),
    EDITABLE_COMMON_TABLE("EDITABLE_COMMON_TABLE", "pages/layout/editableCommonTable.htm"),
    REPORT_REALTIME("REPORT_REALTIME", "pages/layout/reportRealtime.htm"),
    CUSTOM("CUSTOM", ""),
    ;

    private final String code;
    private final String pageLayout;

    WebPageContentType(String code, String pageLayout) {
        this.code = code;
        this.pageLayout = pageLayout;
    }

    public String getPageLayout() {
        return pageLayout;
    }

    public static WebPageContentType getByCode(String code) {
        for (WebPageContentType contentType : values()) {
            if (contentType.code.equals(code)) {
                return contentType;
            }
        }
        return CUSTOM;
    }
}