/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.organization;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SubOrganization.java, v 0.1 2024‐08‐11 8:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SubOrganization {

    private String subOrgId;
    private String orgId;
    private String name;
    private String address;

    public SubOrganization() {
    }

    public SubOrganization(String subOrgId, String name) {
        this.subOrgId = subOrgId;
        this.name = name;
    }

    public String getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(String subOrgId) {
        this.subOrgId = subOrgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}