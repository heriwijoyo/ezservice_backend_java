/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminBOMenu.java, v 0.1 2024‐02‐11 10:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAdminBOMenu {

    private String orgId;
    private String permissionMain;
    private String menuName;
    private String menuUrl;
    private int sorting;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPermissionMain() {
        return permissionMain;
    }

    public void setPermissionMain(String permissionMain) {
        this.permissionMain = permissionMain;
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

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }
}