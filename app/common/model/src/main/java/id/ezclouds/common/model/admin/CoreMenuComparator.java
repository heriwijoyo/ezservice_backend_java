/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.admin;

import java.util.Comparator;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMenuComparator.java, v 0.1 2024‐03‐27 12:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreMenuComparator implements Comparator<CoreAdminMenu> {
    @Override
    public int compare(CoreAdminMenu o1, CoreAdminMenu o2) {
        return o1.getSorting() - o2.getSorting();
    }
}