/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.converter;

import id.ezclouds.common.model.biz.election.BizElectionFeature;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.common.model.core.feature.CoreFeatureConfig;
import id.ezclouds.common.model.core.feature.Feature;
import id.ezclouds.common.model.core.feature.FeatureSection;
import id.ezclouds.common.model.core.feature.FeatureSwitchMode;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.dal.core.dataobject.CoreFeatureConfigDO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeatureConfigConverter.java, v 0.1 2024‐10‐13 5:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreFeatureConfigConverter extends CommonDOModelConverter<CoreFeatureConfigDO, CoreFeatureConfig> {

    @Override
    protected CoreFeatureConfig safeConvertQuery(CoreFeatureConfigDO dataObject) {
        CoreFeatureConfig featureConfig = new CoreFeatureConfig();
        featureConfig.setFeatureConfigId(dataObject.getFeatureConfigId());
        featureConfig.setOrgId(dataObject.getOrgId());
        featureConfig.setSection(FeatureSection.getByCode(dataObject.getFeatureSection()));
        featureConfig.setFeature(parseFeature(dataObject));
        featureConfig.setSwitchMode(FeatureSwitchMode.getByCode(dataObject.getSwitchMode()));
        featureConfig.setUserWhitelist(parseUserWhitelist(dataObject.getUserWhitelist()));
        return featureConfig;
    }

    @Override
    protected CoreFeatureConfigDO safeConvertStore(CoreFeatureConfig model) {
        return null;
    }

    private Feature parseFeature(CoreFeatureConfigDO configDO) {
        FeatureSection featureSection = FeatureSection.getByCode(configDO.getFeatureSection());
        switch (featureSection) {
            case BIZ_ELECTION:
                return BizElectionFeature.getByCode(configDO.getFeatureName());
            case BIZ_COMMERCE:
            case CORE:
        }
        return null;
    }

    private List<String> parseUserWhitelist(String whitelist) {
        if (StringUtil.isBlank(whitelist)) {
            return new ArrayList<>();
        }
        return Arrays.asList(whitelist.split(","));
    }
}