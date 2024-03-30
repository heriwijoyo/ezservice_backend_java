/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.admin;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOrganizationDetail.java, v 0.1 2024‐03‐29 2:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizOrganizationDetail {

    private BizOrganization bizOrganization;
    private BizApplicationConfig bizApplicationConfig;

    public BizOrganization getBizOrganization() {
        return bizOrganization;
    }

    public void setBizOrganization(BizOrganization bizOrganization) {
        this.bizOrganization = bizOrganization;
    }

    public BizApplicationConfig getBizApplicationConfig() {
        return bizApplicationConfig;
    }

    public void setBizApplicationConfig(BizApplicationConfig bizApplicationConfig) {
        this.bizApplicationConfig = bizApplicationConfig;
    }
}