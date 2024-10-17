/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.core.feature;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeatureConfig.java, v 0.1 2024‐10‐13 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreFeatureConfig {

    private String featureConfigId;
    private String orgId;
    private FeatureSection section;
    private Feature feature;
    private FeatureSwitchMode switchMode;
    private List<String> userWhitelist;

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

    public FeatureSection getSection() {
        return section;
    }

    public void setSection(FeatureSection section) {
        this.section = section;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public FeatureSwitchMode getSwitchMode() {
        return switchMode;
    }

    public void setSwitchMode(FeatureSwitchMode switchMode) {
        this.switchMode = switchMode;
    }

    public List<String> getUserWhitelist() {
        return userWhitelist;
    }

    public void setUserWhitelist(List<String> userWhitelist) {
        this.userWhitelist = userWhitelist;
    }
}