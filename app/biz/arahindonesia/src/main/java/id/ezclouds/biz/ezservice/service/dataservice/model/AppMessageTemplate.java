/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMessageTemplate.java, v 0.1 2024‐02‐05 3:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class AppMessageTemplate {

    private String id;
    private String value;

    public AppMessageTemplate(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}