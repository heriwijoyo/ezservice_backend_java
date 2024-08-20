/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ModelAdjuster.java, v 0.1 2024‐08‐11 9:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ModelAdjuster<O> {
    void adjust(O origin);
}