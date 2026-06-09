/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.admin;

import id.ezclouds.biz.election.subbiz.arahindonesia.model.BizSubOrganization;
import id.ezclouds.common.model.area.CoreArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberRequiredData.java, v 0.1 2024‐08‐01 8:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberRequiredData {

    private List<BizSubOrganization> bizSubOrganizations;

    private String workingAreaLevel;

    private List<CoreArea> workingArea;

    public List<BizSubOrganization> getBizSubOrganizations() {
        return bizSubOrganizations;
    }

    public void setBizSubOrganizations(List<BizSubOrganization> bizSubOrganizations) {
        this.bizSubOrganizations = bizSubOrganizations;
    }

    public String getWorkingAreaLevel() {
        return workingAreaLevel;
    }

    public void setWorkingAreaLevel(String workingAreaLevel) {
        this.workingAreaLevel = workingAreaLevel;
    }

    public List<CoreArea> getWorkingArea() {
        return workingArea;
    }

    public void setWorkingArea(List<CoreArea> workingArea) {
        this.workingArea = workingArea;
    }
}