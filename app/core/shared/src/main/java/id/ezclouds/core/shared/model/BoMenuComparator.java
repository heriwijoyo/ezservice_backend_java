/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.core.shared.model.CoreAdminBOMenu;

import java.util.Comparator;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BoMenuComparator.java, v 0.1 2024‐03‐27 12:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BoMenuComparator implements Comparator<CoreAdminBOMenu> {
    @Override
    public int compare(CoreAdminBOMenu o1, CoreAdminBOMenu o2) {
        return o1.getSorting() - o2.getSorting();
    }
}