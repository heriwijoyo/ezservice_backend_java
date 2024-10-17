/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.converter;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.Village;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.area.dataobject.EzVillageDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzVillageQueryConverter.java, v 0.1 2024‐08‐12 6:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzVillageQueryConverter extends TemplateModelConverter<EzVillageDO, CoreArea> {

    @Override
    protected CoreArea safeConvert(EzVillageDO input) {
        return new Village(input.getId(), input.getDistrictId(), input.getName());
    }
}