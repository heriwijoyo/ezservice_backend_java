/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.biz.ezservice.enums.BizUploadScene;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonUploadRequest.java, v 0.1 2024‐07‐04 2:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class BizCommonUploadRequest extends BizMultipartRequest {

    @Override
    public BizUploadScene getScene() {
        return null;
    }

    @Override
    protected void presetScene(BizUploadScene scene) {

    }

    @Override
    protected List<BizUploadScene> getSupportedScene() {
        return null;
    }

    @Override
    protected List<String> getSupportedContentType() {
        return null;
    }
}