/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.config.converter;

import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.config.dataobject.CoreOrgConfigDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreOrgConfigConverter.java, v 0.1 2024‐08‐29 11:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzCoreOrgConfigConverter extends CommonDOModelConverter<CoreOrgConfigDO, CoreConfig> {

    @Override
    protected CoreConfig safeConvertQuery(CoreOrgConfigDO dataObject) {
        CoreConfig coreConfig = new CoreConfig();
        coreConfig.setConfigId(dataObject.getConfigId());
        coreConfig.setOrgId(dataObject.getOrgId());
        coreConfig.setConfigKey(dataObject.getConfigKey());
        coreConfig.setConfigValue(dataObject.getConfigValue());
        return coreConfig;
    }

    @Override
    protected CoreOrgConfigDO safeConvertStore(CoreConfig model) {
        CoreOrgConfigDO configDO = new CoreOrgConfigDO();
        configDO.setConfigId(model.getConfigId());
        configDO.setOrgId(model.getOrgId());
        configDO.setConfigKey(model.getConfigKey());
        configDO.setConfigValue(model.getConfigValue());
        return configDO;
    }
}