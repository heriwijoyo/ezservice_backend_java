/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Province.java, v 0.1 2024‐09‐03 11:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class Province implements CoreArea {

    private String id;
    private String name;

    public Province(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public CoreAreaLevel getAreaLevel() {
        return CoreAreaLevel.PROVINCE;
    }

    @Override
    public String getAreaId() {
        return id;
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}