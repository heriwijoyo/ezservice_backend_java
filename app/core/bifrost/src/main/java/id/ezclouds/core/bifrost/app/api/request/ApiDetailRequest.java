/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiDetailRequest.java, v 0.1 2024‐05‐09 10:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiDetailRequest extends ApiRequest {

    private String detailId;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
}