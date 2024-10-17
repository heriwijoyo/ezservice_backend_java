/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.admin;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminMenuView.java, v 0.1 2024‐09‐23 12:31 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AdminMenuView {

    private String menuName;
    private String menuIcon;
    private String menuUrl;

    public AdminMenuView(String menuName, String menuIcon, String menuUrl) {
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.menuUrl = menuUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }
}