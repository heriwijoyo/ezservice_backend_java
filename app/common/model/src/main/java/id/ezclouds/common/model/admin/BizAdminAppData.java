/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.admin;

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
    private List<AdminMenuView> menu;

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

    public List<AdminMenuView> getMenu() {
        return menu;
    }

    public void setMenu(List<AdminMenuView> menu) {
        this.menu = menu;
    }
}