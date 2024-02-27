/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web.request;

import id.ezclouds.biz.ezservice.enums.WebLoadImageScene;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebLoadImageRequest.java, v 0.1 2024‐02‐09 12:05 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebLoadImageRequest {

    private WebLoadImageScene scene;
    private String orgCode;
    private String memberId;
    private String fileName;

    public WebLoadImageScene getScene() {
        return scene;
    }

    public void setScene(WebLoadImageScene scene) {
        this.scene = scene;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}