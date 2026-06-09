/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.converter;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.Province;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.area.dataobject.EzProvinceDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzProvinceQueryConverter.java, v 0.1 2024‐09‐07 2:16 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzProvinceQueryConverter extends TemplateModelConverter<EzProvinceDO, CoreArea> {

    @Override
    protected CoreArea safeConvert(EzProvinceDO input) {
        return new Province(input.getId(), input.getName());
    }
}