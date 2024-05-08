/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppProfileDO.java, v 0.1 2023‐12‐11 12:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_profile")
public class AppProfileDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "profile_key")
    private String profileKey;

    @Column(name = "profile_value")
    private String profileValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getProfileKey() {
        return profileKey;
    }

    public void setProfileKey(String profileKey) {
        this.profileKey = profileKey;
    }

    public String getProfileValue() {
        return profileValue;
    }

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }
}