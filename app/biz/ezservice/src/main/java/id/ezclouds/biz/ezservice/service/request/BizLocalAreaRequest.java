/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.common.model.request.BizRequest;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizLocalAreaRequest.java, v 0.1 2024‐02‐18 7:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizLocalAreaRequest extends BizRequest {

    private String areaLevel;
    private List<String> areaIds;
    private List<String> parentIds;

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public List<String> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(List<String> areaIds) {
        this.areaIds = areaIds;
    }

    public List<String> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<String> parentIds) {
        this.parentIds = parentIds;
    }
}