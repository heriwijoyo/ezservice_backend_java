/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.config;

import id.ezclouds.common.model.config.CoreConfig;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreConfigDAO.java, v 0.1 2024‐08‐29 11:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreConfigDAO {

    CoreConfig getConfig(String orgId, String configKey);

    void storeConfig(CoreConfig coreConfig);
}