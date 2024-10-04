/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal;

import javax.persistence.Column;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuditableDO.java, v 0.1 2024‐10‐05 3:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAuditableDO {

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "modified_time")
    private String modifiedTime;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}