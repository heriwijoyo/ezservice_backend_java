/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area;

import id.ezclouds.common.facade.dal.area.AreaProvinceDAO;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.core.dal.area.converter.EzProvinceQueryConverter;
import id.ezclouds.core.dal.area.repo.EzProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaProvinceDAO.java, v 0.1 2024‐09‐07 2:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAreaProvinceDAO implements AreaProvinceDAO {

    @Autowired
    private EzProvinceRepository ezProvinceRepository;

    @Override
    public CoreArea getById(String areaId) {
        return new EzProvinceQueryConverter().convert(
                ezProvinceRepository
                        .findById(areaId)
                        .orElse(null)
        );
    }
}