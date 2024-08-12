/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area;

import id.ezclouds.common.facade.dal.area.AreaDistrictDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.area.District;
import id.ezclouds.common.model.util.ListModelConvertUtil;
import id.ezclouds.core.dal.area.converter.EzDistrictQueryConverter;
import id.ezclouds.core.dal.area.repo.EzDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaDistrictDAO.java, v 0.1 2024‐08‐12 4:56 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAreaDistrictDAO implements AreaDistrictDAO {

    @Autowired
    private EzDistrictRepository ezDistrictRepository;

    @EzDAOLogger
    @Override
    public List<District> getByRegencyId(String regencyId) {
        return ListModelConvertUtil.convert(
                ezDistrictRepository.findByRegencyIdIn(Collections.singletonList(regencyId)),
                new EzDistrictQueryConverter()
        );
    }

    @EzDAOLogger
    @Override
    public List<District> getByRegencyIds(List<String> regencyIds) {
        return ListModelConvertUtil.convert(
                ezDistrictRepository.findByRegencyIdIn(regencyIds),
                new EzDistrictQueryConverter()
        );
    }
}