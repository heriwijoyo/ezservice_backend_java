/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.area;

import id.ezclouds.common.model.area.CoreArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AreaDistrictDAO.java, v 0.1 2024‐08‐12 4:47 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AreaDistrictDAO {

    List<CoreArea> getByRegencyId(String regencyId);

    List<CoreArea> getByRegencyIds(List<String> regencyIds);

    CoreArea getById(String areaId);
}