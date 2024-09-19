/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.area;

import id.ezclouds.common.model.area.CoreArea;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaScanListener.java, v 0.1 2024‐09‐16 7:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreAreaScanListener {

    void areaOnTargetLevel(CoreArea currentArea);
}