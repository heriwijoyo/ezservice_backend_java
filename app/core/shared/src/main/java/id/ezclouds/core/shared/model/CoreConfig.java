/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.common.util.StringUtil;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConfig.java, v 0.1 2024‐03‐31 3:08 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreConfig {

    private String configId;
    private String configKey;
    private String configValue;

    public CoreConfig() {
        configValue = StringUtil.EMPTY;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}