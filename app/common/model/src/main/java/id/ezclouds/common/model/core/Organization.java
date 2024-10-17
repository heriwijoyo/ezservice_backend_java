/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Organization.java, v 0.1 2024‐09‐22 6:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class Organization {

    private String orgId;
    private String orgCode;
    private String orgName;

    public Organization(String orgId, String orgCode) {
        this(orgId, orgCode, null);
    }

    public Organization(String orgId, String orgCode, String orgName) {
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}