/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Regency.java, v 0.1 2024‐09‐03 11:23 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class Regency implements CoreArea {

    private String id;
    private String provinceId;
    private String name;

    public Regency(String id, String provinceId, String name) {
        this.id = id;
        this.provinceId = provinceId;
        this.name = name;
    }

    @Override
    public CoreAreaLevel getAreaLevel() {
        return CoreAreaLevel.REGENCY;
    }

    @Override
    public String getAreaId() {
        return id;
    }

    @Override
    public String getParentId() {
        return provinceId;
    }

    @Override
    public String getName() {
        return name;
    }
}