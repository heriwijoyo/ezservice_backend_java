/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.election.model;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppUpdateInfo.java, v 0.1 2023‐12‐10 12:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppUpdateInfo {

    private String title;
    private String message;
    private String updateUrl;
    private boolean needForceUpdate = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public boolean isNeedForceUpdate() {
        return needForceUpdate;
    }

    public void setNeedForceUpdate(boolean needForceUpdate) {
        this.needForceUpdate = needForceUpdate;
    }
}