/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.config.converter;

import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.config.dataobject.CoreConfigDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConfigConverter.java, v 0.1 2024‐09‐08 1:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzCoreConfigConverter extends CommonDOModelConverter<CoreConfigDO, CoreConfig> {

    @Override
    protected CoreConfig safeConvertQuery(CoreConfigDO dataObject) {
        CoreConfig coreConfig = new CoreConfig();
        coreConfig.setConfigId(dataObject.getConfigId());
        coreConfig.setConfigKey(dataObject.getConfigKey());
        coreConfig.setConfigValue(dataObject.getConfigValue());
        return coreConfig;
    }

    @Override
    protected CoreConfigDO safeConvertStore(CoreConfig model) {
        CoreConfigDO coreConfigDO = new CoreConfigDO();
        coreConfigDO.setConfigId(model.getConfigId());
        coreConfigDO.setConfigKey(model.getConfigKey());
        coreConfigDO.setConfigValue(model.getConfigValue());
        return coreConfigDO;
    }
}