/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

import id.ezclouds.core.shared.enums.CoreAreaLevel;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreArea.java, v 0.1 2024‐02‐18 6:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreArea {

    private String id;
    private String name;
    private CoreAreaLevel areaLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoreAreaLevel getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(CoreAreaLevel areaLevel) {
        this.areaLevel = areaLevel;
    }
}