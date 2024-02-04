/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.common.util.StringUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConfig.java, v 0.1 2024‐02‐04 4:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class CoreConfig {

    private String configId;
    private String orgId;
    private String configValue;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public static CoreConfig EMPTY = createEmptyCoreConfig();

    private static CoreConfig createEmptyCoreConfig() {
        CoreConfig coreConfig = new CoreConfig();
        coreConfig.setConfigValue(StringUtil.EMPTY);
        return coreConfig;
    }
}