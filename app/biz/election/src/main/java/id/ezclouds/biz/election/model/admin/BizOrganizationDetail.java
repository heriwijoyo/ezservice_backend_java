/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.model.admin;

import id.ezclouds.biz.election.service.app.model.BizAppBuildPackage;
import id.ezclouds.biz.election.service.app.model.BizAppConfig;
import id.ezclouds.biz.election.model.member.BizMember;
import id.ezclouds.common.model.core.organization.BizOrganization;

import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizOrganizationDetail.java, v 0.1 2024‐03‐29 2:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizOrganizationDetail {

    private BizOrganization bizOrganization;
    private BizApplicationConfig bizApplicationConfig;
    private List<BizAppConfig> bizAppConfigs;
    private Map<String, String> coreOrgConfigMap;
    private List<BizMember> adminMembers;
    private List<BizAppBuildPackage> bizAppBuildPackages;

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

    public List<BizAppConfig> getBizAppConfigs() {
        return bizAppConfigs;
    }

    public void setBizAppConfigs(List<BizAppConfig> bizAppConfigs) {
        this.bizAppConfigs = bizAppConfigs;
    }

    public Map<String, String> getCoreOrgConfigMap() {
        return coreOrgConfigMap;
    }

    public void setCoreOrgConfigMap(Map<String, String> coreOrgConfigMap) {
        this.coreOrgConfigMap = coreOrgConfigMap;
    }

    public List<BizMember> getAdminMembers() {
        return adminMembers;
    }

    public void setAdminMembers(List<BizMember> adminMembers) {
        this.adminMembers = adminMembers;
    }

    public List<BizAppBuildPackage> getBizAppBuildPackages() {
        return bizAppBuildPackages;
    }

    public void setBizAppBuildPackages(List<BizAppBuildPackage> bizAppBuildPackages) {
        this.bizAppBuildPackages = bizAppBuildPackages;
    }
}