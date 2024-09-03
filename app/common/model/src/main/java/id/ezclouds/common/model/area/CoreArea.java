/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreArea.java, v 0.1 2024‐09‐03 11:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CoreArea {

    CoreAreaLevel getAreaLevel();
    String getAreaId();
    String getParentId();
    String getName();

}