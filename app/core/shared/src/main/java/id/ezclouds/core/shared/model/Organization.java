/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Organization.java, v 0.1 2023‐12‐10 12:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class Organization {

    private String orgId;
    private String name;
    private String code;

    public Organization(String orgId, String name, String code) {
        this.orgId = orgId;
        this.name = name;
        this.code = code;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}