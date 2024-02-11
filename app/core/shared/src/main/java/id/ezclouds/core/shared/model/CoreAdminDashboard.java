/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminDashboard.java, v 0.1 2024‐02‐12 1:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAdminDashboard {

    private String orgId;
    private String keyName;
    private String displayName;
    private String icon;
    private String url;
    private int countValue;
    private String countLabel;
    private String lastUpdate;
    private int sorting;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }

    public String getCountLabel() {
        return countLabel;
    }

    public void setCountLabel(String countLabel) {
        this.countLabel = countLabel;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }
}