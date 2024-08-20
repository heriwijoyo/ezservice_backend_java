/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.admin;

import id.ezclouds.biz.ezservice.model.member.BizMember;
import id.ezclouds.biz.ezservice.subbiz.arahindonesia.model.BizSubOrganization;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRequiredData.java, v 0.1 2024‐08‐01 8:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberRequiredData {

    private List<BizMember> adminMembers;
    private List<BizSubOrganization> bizSubOrganizations;

    public List<BizMember> getAdminMembers() {
        return adminMembers;
    }

    public void setAdminMembers(List<BizMember> adminMembers) {
        this.adminMembers = adminMembers;
    }

    public List<BizSubOrganization> getBizSubOrganizations() {
        return bizSubOrganizations;
    }

    public void setBizSubOrganizations(List<BizSubOrganization> bizSubOrganizations) {
        this.bizSubOrganizations = bizSubOrganizations;
    }
}