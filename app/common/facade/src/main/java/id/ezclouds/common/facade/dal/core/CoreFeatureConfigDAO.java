/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.core;

import id.ezclouds.common.model.core.feature.CoreFeatureConfig;
import id.ezclouds.common.model.core.feature.Feature;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeatureConfigDAO.java, v 0.1 2024‐10‐13 5:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreFeatureConfigDAO {

    CoreFeatureConfig getConfig(String orgId, Feature feature);
}