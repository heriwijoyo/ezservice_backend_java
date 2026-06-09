/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.core.CoreFeatureConfigService;
import id.ezclouds.common.facade.dal.core.CoreFeatureConfigDAO;
import id.ezclouds.common.model.core.feature.CoreFeatureConfig;
import id.ezclouds.common.model.core.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreFeatureConfigService.java, v 0.1 2024‐10‐13 6:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreFeatureConfigService implements CoreFeatureConfigService {

    @Autowired
    private CoreFeatureConfigDAO coreFeatureConfigDAO;

    @Override
    public boolean isFeatureOpen(String orgId, Feature feature, String userId) {
        CoreFeatureConfig featureConfig = coreFeatureConfigDAO.getConfig(orgId, feature);
        if (featureConfig == null) {
            return true;
        }
        switch (featureConfig.getSwitchMode()) {
            case OPEN:
                return true;
            case CLOSE:
                return false;
            case GREY:
                return featureConfig.getUserWhitelist().contains(userId);
        }
        return false;
    }

}