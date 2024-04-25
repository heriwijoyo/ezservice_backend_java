/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.request;

import id.ezclouds.biz.ezservice.service.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSubOrgCreateRequest.java, v 0.1 2024‐04‐25 9:43 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizSubOrgCreateRequest extends BizRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}