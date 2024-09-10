/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area;

import id.ezclouds.common.facade.dal.area.AreaRegencyDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.core.dal.area.converter.EzRegencyQueryConverter;
import id.ezclouds.core.dal.area.repo.EzRegencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaRegencyDAO.java, v 0.1 2024‐09‐07 1:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAreaRegencyDAO implements AreaRegencyDAO {

    @Autowired
    private EzRegencyRepository ezRegencyRepository;

    @EzDAOLogger
    @Override
    public CoreArea getById(String areaId) {
        return new EzRegencyQueryConverter().convert(
                ezRegencyRepository
                        .findById(areaId)
                        .orElse(null)
        );
    }

    @EzDAOLogger
    @Override
    public List<CoreArea> getByProvinceId(String provinceId) {
        EzRegencyQueryConverter converter = new EzRegencyQueryConverter();
        return ezRegencyRepository
                .findByProvinceId(provinceId)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}