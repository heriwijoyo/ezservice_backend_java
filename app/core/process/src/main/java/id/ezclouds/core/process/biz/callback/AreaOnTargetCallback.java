/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.callback;

import id.ezclouds.common.model.area.CoreArea;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AreaOnTargetCallback.java, v 0.1 2024‐09‐16 7:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AreaOnTargetCallback {

    void onTarget(CoreArea currentArea);
}