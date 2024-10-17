/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.core;

import id.ezclouds.common.model.core.feature.Feature;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeatureConfigService.java, v 0.1 2024‐10‐13 6:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreFeatureConfigService {

    boolean isFeatureOpen(String orgId, Feature feature, String userId);
}