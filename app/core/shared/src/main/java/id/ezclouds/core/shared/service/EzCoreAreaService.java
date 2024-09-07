/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.common.facade.area.CoreAreaService;
import id.ezclouds.common.facade.dal.area.AreaDistrictDAO;
import id.ezclouds.common.facade.dal.area.AreaProvinceDAO;
import id.ezclouds.common.facade.dal.area.AreaRegencyDAO;
import id.ezclouds.common.facade.dal.area.AreaVillageDAO;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.util.CoreAreaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreAreaService.java, v 0.1 2024‐09‐03 11:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class EzCoreAreaService implements CoreAreaService {

    @Autowired
    private AreaProvinceDAO areaProvinceDAO;

    @Autowired
    private AreaRegencyDAO areaRegencyDAO;

    @Autowired
    private AreaDistrictDAO areaDistrictDAO;

    @Autowired
    private AreaVillageDAO areaVillageDAO;

    @Override
    public List<CoreArea> getChildArea(CoreArea parentArea) {
        switch (parentArea.getAreaLevel()) {
            case REGENCY:
                return areaDistrictDAO.getByRegencyId(parentArea.getAreaId());
            case DISTRICT:
                return areaVillageDAO.getByDistrictId(parentArea.getAreaId());

        }
        return new ArrayList<>();
    }

    @Override
    public List<CoreArea> getParentAreaRecursive(List<CoreArea> coreAreas) {
        List<CoreArea> areas = new ArrayList<>();

        CoreArea coreArea = null;
        for (CoreArea area : coreAreas) {
            if (coreArea != null) {
                areas.add(CoreAreaUtil.copyCoreArea(area));
            } else {
                coreArea = getAreaByLevelAndId(area.getAreaLevel(), area.getAreaId());
            }
        }

        while (coreArea != null) {
            areas.add(CoreAreaUtil.copyCoreArea(coreArea));
            coreArea = getParentArea(coreArea);
        }

        return areas;
    }

    private CoreArea getParentArea(CoreArea currentArea) {
        switch (currentArea.getAreaLevel()) {
            case VILLAGE:
                return areaDistrictDAO.getById(currentArea.getParentId());
            case DISTRICT:
                return areaRegencyDAO.getById(currentArea.getParentId());
            case REGENCY:
                return areaProvinceDAO.getById(currentArea.getParentId());
        }
        return null;
    }

    private CoreArea getAreaByLevelAndId(CoreAreaLevel level, String id) {
        switch (level) {
            case REGENCY:
                return areaRegencyDAO.getById(id);
        }
        return null;
    }
}