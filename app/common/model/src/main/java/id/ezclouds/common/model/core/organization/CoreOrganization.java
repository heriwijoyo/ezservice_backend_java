/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.model.core.organization;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrganization.java, v 0.1 2023‐12‐10 12:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreOrganization {

    private String orgId;
    private String name;
    private String code;
    private Map<String, String> extendInfo = new HashMap<>();

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, String> extendInfo) {
        this.extendInfo = extendInfo;
    }
}