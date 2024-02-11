/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.admin;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminAppData.java, v 0.1 2024‐02‐11 6:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAdminAppData {

    private String memberId;
    private List<String> boMenu;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<String> getBoMenu() {
        return boMenu;
    }

    public void setBoMenu(List<String> boMenu) {
        this.boMenu = boMenu;
    }
}