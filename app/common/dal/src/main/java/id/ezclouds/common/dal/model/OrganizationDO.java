/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Organization.java, v 0.1 2023‐12‐04 2:52 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
@Entity
@Table(name = "app_organization")
public class OrganizationDO {

    private String orgId;
    private String name;

    public OrganizationDO() {}

    public OrganizationDO(String orgId, String name) {
        this.orgId = orgId;
        this.name = name;
    }

    @Id
    @Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}