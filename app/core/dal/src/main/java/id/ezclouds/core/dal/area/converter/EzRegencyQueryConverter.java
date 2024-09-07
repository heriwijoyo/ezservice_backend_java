/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.converter;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.Regency;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.area.dataobject.EzRegencyDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzRegencyQueryConverter.java, v 0.1 2024‐09‐07 2:02 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzRegencyQueryConverter extends TemplateModelConverter<EzRegencyDO, CoreArea> {

    @Override
    protected CoreArea safeConvert(EzRegencyDO input) {
        return new Regency(input.getId(), input.getProvinceId(), input.getName());
    }
}