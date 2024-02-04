/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfig.java, v 0.1 2023‐12‐09 9:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Entity
@Table(name = "app_config")
public class AppConfigDO {

    private String id;
    private String orgId;
    private String appName;
    private int versionCode;
    private String versionName;
    private String updateUrl;
    private int needForceUpdate;
    private int sliderAnimationDuration;
    private int maxTpsNumber;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "app_name")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Column(name = "android_version_code")
    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    @Column(name = "android_version_name")
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Column(name = "android_update_url")
    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    @Column(name = "need_force_update")
    public int getNeedForceUpdate() {
        return needForceUpdate;
    }

    public void setNeedForceUpdate(int needForceUpdate) {
        this.needForceUpdate = needForceUpdate;
    }

    @Column(name = "slider_animation_duration")
    public int getSliderAnimationDuration() {
        return sliderAnimationDuration;
    }

    public void setSliderAnimationDuration(int sliderAnimationDuration) {
        this.sliderAnimationDuration = sliderAnimationDuration;
    }

    @Column(name = "max_tps_number")
    public int getMaxTpsNumber() {
        return maxTpsNumber;
    }

    public void setMaxTpsNumber(int maxTpsNumber) {
        this.maxTpsNumber = maxTpsNumber;
    }
}