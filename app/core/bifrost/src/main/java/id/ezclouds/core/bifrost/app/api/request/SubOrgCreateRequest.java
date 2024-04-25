/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SubOrgCreateRequest.java, v 0.1 2024‐04‐25 9:36 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SubOrgCreateRequest extends ApiRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}