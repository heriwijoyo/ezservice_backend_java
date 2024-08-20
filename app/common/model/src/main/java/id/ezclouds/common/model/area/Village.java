/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.area;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Village.java, v 0.1 2024‐08‐12 4:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class Village {

    private String id;
    private String districtId;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}