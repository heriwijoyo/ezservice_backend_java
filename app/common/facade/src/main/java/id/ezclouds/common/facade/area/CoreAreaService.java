/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.area;

import id.ezclouds.common.model.area.CoreArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaService.java, v 0.1 2024‐09‐03 11:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreAreaService {

    List<CoreArea> getChildArea(CoreArea parentArea);
}