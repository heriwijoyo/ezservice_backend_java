/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.admin;

import id.ezclouds.core.shared.model.CoreAdminBOMenu;
import id.ezclouds.core.shared.model.CoreAdminBOPermission;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminAppData.java, v 0.1 2024‐02‐11 6:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAdminAppData {

    private String orgName;
    private String orgCode;
    private String memberId;
    private String memberName;
    private String memberPhone;
    private List<CoreAdminBOPermission> permission;
    private List<CoreAdminBOMenu> menu;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public List<CoreAdminBOPermission> getPermission() {
        return permission;
    }

    public void setPermission(List<CoreAdminBOPermission> permission) {
        this.permission = permission;
    }

    public List<CoreAdminBOMenu> getMenu() {
        return menu;
    }

    public void setMenu(List<CoreAdminBOMenu> menu) {
        this.menu = menu;
    }
}