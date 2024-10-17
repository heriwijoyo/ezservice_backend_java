/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.area;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.Village;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AreaVillageDAO.java, v 0.1 2024‐08‐12 4:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AreaVillageDAO {

    List<CoreArea> getByDistrictId(String districtId);

    List<CoreArea> getByDistrictIds(List<String> districtIds);

    CoreArea getById(String areaId);
}