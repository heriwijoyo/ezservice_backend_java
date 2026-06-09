/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.app;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzApplication.java, v 0.1 2024‐08‐12 8:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzApplication {

    private String id;
    private EzAppPlatform platform;
    private String orgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EzAppPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(EzAppPlatform platform) {
        this.platform = platform;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}