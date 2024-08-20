/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: FileStreamImportRequest.java, v 0.1 2024‐08‐11 5:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class FileStreamImportRequest extends FileStreamRequest {

    private String orgId;
    private String subOrgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(String subOrgId) {
        this.subOrgId = subOrgId;
    }
}