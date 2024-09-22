/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.admin;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminMenu.java, v 0.1 2024‐02‐11 10:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public enum CoreAdminMenu {

    APP_IMAGE_GALLERY(CoreMenuLevel.ADMIN_ORGANIZATION, "Manage Gallery", "appgallery.htm", "image", 10),
    APP_NEWS(CoreMenuLevel.ADMIN_ORGANIZATION, "Manage News", "news.htm", "library_books", 20),
    APP_EVENTS(CoreMenuLevel.ADMIN_ORGANIZATION, "Manage Events", "events.htm", "calendar_month", 30),
    APP_VIDEO_CARD(CoreMenuLevel.ADMIN_ORGANIZATION, "Manage Video Card", "videocard.htm", "smart_display", 40),
    APP_PROFILE(CoreMenuLevel.ADMIN_ORGANIZATION, "Candidate Profile", "profile.htm", "assignment_ind", 50),
    APP_DOCUMENTS(CoreMenuLevel.ADMIN_ORGANIZATION, "Documents", "documents.htm", "picture_as_pdf", 60),
    WHATSAPP_LOG(CoreMenuLevel.ADMIN_ORGANIZATION, "Whatsapp Logs", "whatsapp.htm", "sms", 70),
    COMMUNITIES(CoreMenuLevel.ADMIN_ORGANIZATION, "Communities", "subOrganizations.htm", "groups", 80),
    MEMBERS(CoreMenuLevel.ADMIN_ORGANIZATION, "Members", "members.htm", "group", 90),
    DATA_UPLOAD(CoreMenuLevel.ADMIN_ORGANIZATION, "Data Upload", "dataUpload.htm", "upload_file", 100),
    MASTER_DATA(CoreMenuLevel.ADMIN_ORGANIZATION, "Master Data", "masterData.htm", "storage", 120),

    ORGANIZATION(CoreMenuLevel.SUPERADMIN, "Manage Organization", "organization.htm", "settings", 10),
    SYSTEM_CONFIG(CoreMenuLevel.SUPERADMIN, "System Config", "config.htm", "settings", 20),
    COMMON_TABLES(CoreMenuLevel.SUPERADMIN, "Common Tables", "commonTables.htm", "table_view", 30),

    ;

    CoreAdminMenu(CoreMenuLevel menuLevel, String menuName, String menuUrl, String menuIcon, int sorting) {
        this.menuLevel = menuLevel;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.sorting = sorting;
    }

    private CoreMenuLevel menuLevel;
    private String orgId;
    private String permissionMain;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private int sorting;

    public CoreMenuLevel getMenuLevel() {
        return menuLevel;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPermissionMain() {
        return permissionMain;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }
}