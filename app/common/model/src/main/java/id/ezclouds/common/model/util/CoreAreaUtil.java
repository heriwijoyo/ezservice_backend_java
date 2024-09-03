/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.area.*;

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

    public static CoreArea buildCoreArea(CoreAreaLevel level, String areaId, String areaName) {
        switch (level) {
            case PROVINCE:
                return new Province(areaId, areaName);
            case REGENCY:
                return new Regency(areaId, null, areaName);
            case DISTRICT:
                return new District(areaId, null, areaName);
            case VILLAGE:
                return new Village(areaId, null, areaName);
        }
        return null;
    }
}