/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.area;

import id.ezclouds.common.model.area.CoreArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AreaRegencyDAO.java, v 0.1 2024‐09‐07 1:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AreaRegencyDAO {

    CoreArea getById(String areaId);

    List<CoreArea> getByProvinceId(String provinceId);
}