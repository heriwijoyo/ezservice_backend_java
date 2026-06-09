/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.biz.data;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataQueryParam.java, v 0.1 2024‐09‐20 1:53 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMasterDataQueryParam {

    private String orgId;
    private BizMasterDataScene scene;
    private String provinceId;
    private String regencyId;
    private String districtId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public BizMasterDataScene getScene() {
        return scene;
    }

    public void setScene(BizMasterDataScene scene) {
        this.scene = scene;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getRegencyId() {
        return regencyId;
    }

    public void setRegencyId(String regencyId) {
        this.regencyId = regencyId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}