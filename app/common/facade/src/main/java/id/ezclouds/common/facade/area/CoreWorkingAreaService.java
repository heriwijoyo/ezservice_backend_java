/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.area;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;

import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreWorkingAreaService.java, v 0.1 2024‐09‐19 12:43 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreWorkingAreaService {

    void scanWorkingAreaRecursive(String orgId, CoreAreaLevel targetLevel, CoreAreaScanListener listener);

    List<CoreArea> fetchCoreAreas(String orgId, CoreAreaLevel targetLevel);

    Map<String, CoreArea> allParentMap();
}