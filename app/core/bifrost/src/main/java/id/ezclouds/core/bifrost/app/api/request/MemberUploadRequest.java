/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

import id.ezclouds.common.model.request.api.ApiRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUploadRequest.java, v 0.1 2024‐02‐10 12:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberUploadRequest extends ApiRequest {

    private String scene;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}