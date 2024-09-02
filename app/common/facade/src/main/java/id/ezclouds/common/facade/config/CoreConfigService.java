/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.config;

import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.config.CoreConfigType;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConfigService.java, v 0.1 2024‐09‐01 10:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreConfigService {

    CoreConfig getOrgConfig(String orgId, CoreConfigType configType);

    CoreConfig getOrgConfig(CoreConfigType configType);

    Map<CoreConfigType, CoreConfig> getOrgConfigMap(String orgId);

    Map<CoreConfigType, CoreConfig> getOrgConfigMap(String orgId, CoreConfigType... configTypes);

    Map<String, String> getOrgRawConfigMap(String orgId);

    void store(CoreConfig coreConfig);

}