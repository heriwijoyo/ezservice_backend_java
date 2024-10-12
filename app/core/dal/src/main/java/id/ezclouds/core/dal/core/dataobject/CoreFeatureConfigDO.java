/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeatureConfigDO.java, v 0.1 2024‐10‐13 5:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "ez_core_feature_config")
public class CoreFeatureConfigDO {

    @Id
    @Column(name = "feature_config_id")
    private String featureConfigId;
    @Column(name = "org_id")
    private String orgId;
    @Column(name = "feature_section")
    private String featureSection;
    @Column(name = "feature_name")
    private String featureName;
    @Column(name = "switch_mode")
    private String switchMode;
    @Column(name = "user_whitelist")
    private String userWhitelist;

    public String getFeatureConfigId() {
        return featureConfigId;
    }

    public void setFeatureConfigId(String featureConfigId) {
        this.featureConfigId = featureConfigId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getFeatureSection() {
        return featureSection;
    }

    public void setFeatureSection(String featureSection) {
        this.featureSection = featureSection;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getSwitchMode() {
        return switchMode;
    }

    public void setSwitchMode(String switchMode) {
        this.switchMode = switchMode;
    }

    public String getUserWhitelist() {
        return userWhitelist;
    }

    public void setUserWhitelist(String userWhitelist) {
        this.userWhitelist = userWhitelist;
    }
}