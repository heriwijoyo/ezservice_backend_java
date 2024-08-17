/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.webapp;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebAppPage.java, v 0.1 2024‐04‐27 9:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum WebAppPage {

    HOME("webapp/home.htm"),
    GALLERY("webapp/appgallery.htm"),
    NEWS("webapp/news.htm"),
    NEWS_ADD("webapp/newsAdd.htm"),
    NEWS_UPDATE("webapp/newsUpdate.htm"),
    EVENT("webapp/events.htm"),
    EVENT_ADD("webapp/eventAdd.htm"),
    EVENT_UPDATE("webapp/eventUpdate.htm"),
    VIDEO_CARD("webapp/videocard.htm"),
    PROFILE("webapp/profile.htm"),
    WHATSAPP("webapp/whatsapp.htm"),
    DOCUMENTS("webapp/documents.htm"),

    SUB_ORGANIZATIONS("webapp/subOrganizations.htm"),
    MEMBERS("webapp/members.htm"),
    MEMBER_ADD("webapp/memberAdd.htm"),
    MEMBER_DETAIL("webapp/memberDetail.htm"),
    DATA_UPLOAD("webapp/dataUpload.htm"),

    ORGANIZATION("webapp/organization.htm"),
    ORGANIZATION_ADD("webapp/organizationAdd.htm"),
    ORGANIZATION_DETAIL("webapp/organizationDetail.htm"),
    ADD_MEMBER("webapp/addMember.htm"),
    CONFIG("webapp/config.htm"),
    SPECIAL_PROCESS("webapp/specialProcess.htm"),
    DATA_PUBLIC_LIMITED("webapp/data.htm"),
    REPORT_PUBLIC_LIMITED("webapp/report.htm"),

    COMMON_TABLES("webapp/commonTables.htm"),
    COMMON_TABLE_ADD("webapp/commonTableAdd.htm"),
    COMMON_TABLE_DETAIL("webapp/commonTableDetail.htm"),

    ;

    private final String assetFile;

    WebAppPage(String assetFile) {
        this.assetFile = assetFile;
    }

    public String getAssetFile() {
        return assetFile;
    }
}