/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizDetailRequest.java, v 0.1 2024‐05‐09 10:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizDetailRequest extends BizRequest {

    private String detailId;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
}