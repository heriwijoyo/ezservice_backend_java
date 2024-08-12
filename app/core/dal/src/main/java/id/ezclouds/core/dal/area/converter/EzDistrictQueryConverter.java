/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.converter;

import id.ezclouds.common.model.area.District;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.area.dataobject.EzDistrictDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDistrictQueryConverter.java, v 0.1 2024‐08‐12 5:43 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzDistrictQueryConverter extends TemplateModelConverter<EzDistrictDO, District> {

    @Override
    protected District safeConvert(EzDistrictDO input) {
        District district = new District();
        district.setId(input.getId());
        district.setRegencyId(input.getRegencyId());
        district.setName(input.getName());
        return district;
    }
}