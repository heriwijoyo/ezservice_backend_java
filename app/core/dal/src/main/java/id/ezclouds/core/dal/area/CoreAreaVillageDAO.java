/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area;

import id.ezclouds.common.facade.dal.area.AreaVillageDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.area.Village;
import id.ezclouds.common.model.util.ListModelConvertUtil;
import id.ezclouds.core.dal.area.converter.EzVillageQueryConverter;
import id.ezclouds.core.dal.area.repo.EzVillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaVillageDAO.java, v 0.1 2024‐08‐12 6:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreAreaVillageDAO implements AreaVillageDAO {

    @Autowired
    private EzVillageRepository ezVillageRepository;

    @EzDAOLogger
    @Override
    public List<Village> getByDistrictId(String districtId) {
        return ListModelConvertUtil.convert(
                ezVillageRepository.findByDistrictIdIn(Collections.singletonList(districtId)),
                new EzVillageQueryConverter()
        );
    }

    @EzDAOLogger
    @Override
    public List<Village> getByDistrictIds(List<String> districtIds) {
        return ListModelConvertUtil.convert(
                ezVillageRepository.findByDistrictIdIn(districtIds),
                new EzVillageQueryConverter()
        );
    }
}