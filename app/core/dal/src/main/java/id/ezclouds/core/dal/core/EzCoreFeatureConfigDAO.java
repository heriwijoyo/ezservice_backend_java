/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core;

import id.ezclouds.common.facade.dal.core.CoreFeatureConfigDAO;
import id.ezclouds.common.model.core.feature.CoreFeatureConfig;
import id.ezclouds.common.model.core.feature.Feature;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.dal.core.converter.CoreFeatureConfigConverter;
import id.ezclouds.core.dal.core.repo.CoreFeatureConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreFeatureConfigDAO.java, v 0.1 2024‐10‐13 5:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class EzCoreFeatureConfigDAO implements CoreFeatureConfigDAO {

    @Autowired
    private CoreFeatureConfigRepository coreFeatureConfigRepository;

    @Override
    public CoreFeatureConfig getConfig(String orgId, Feature feature) {
        String featureConfigId = HashUtil.createHash(orgId, feature.getCode());
        return new CoreFeatureConfigConverter()
                .convertQuery(
                        coreFeatureConfigRepository
                                .findById(featureConfigId)
                                .orElse(null)
                );
    }
}