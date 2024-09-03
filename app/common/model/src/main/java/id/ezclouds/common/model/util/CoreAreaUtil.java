/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.area.CoreAreaLevel;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAreaUtil.java, v 0.1 2024‐09‐03 11:40 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAreaUtil {

    public static CoreAreaLevel getLowerLevel(CoreAreaLevel currentLevel) {
        switch (currentLevel) {
            case PROVINCE:
                return CoreAreaLevel.REGENCY;
            case REGENCY:
                return CoreAreaLevel.DISTRICT;
            case DISTRICT:
                return CoreAreaLevel.VILLAGE;

            case VILLAGE:
            default:
                return null;
        }
    }
}