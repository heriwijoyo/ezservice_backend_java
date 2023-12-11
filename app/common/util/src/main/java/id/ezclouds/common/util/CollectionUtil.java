/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.util;

import java.util.Collection;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CollectionUtil.java, v 0.1 2023‐12‐11 10:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CollectionUtil {

    public static boolean isNotEmpty(Collection collection) {
        if (collection == null) {
            return false;
        }
        return !collection.isEmpty();
    }
}