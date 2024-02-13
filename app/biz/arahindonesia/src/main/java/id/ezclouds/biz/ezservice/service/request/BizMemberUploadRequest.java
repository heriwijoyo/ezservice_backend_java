/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.biz.ezservice.constant.BizUploadScene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUploadRequest.java, v 0.1 2024‐02‐07 3:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberUploadRequest extends BizMultipartRequest {

    private BizUploadScene scene;

    @Override
    public BizUploadScene getScene() {
        return scene;
    }

    public void setScene(BizUploadScene scene) {
        this.scene = scene;
    }

    @Override
    protected List<BizUploadScene> getSupportedScene() {
        List<BizUploadScene> supportedScene = new ArrayList<>();
        supportedScene.add(BizUploadScene.AVATAR);
        supportedScene.add(BizUploadScene.ID_CARD);
        supportedScene.add(BizUploadScene.FAMILY_CARD);
        supportedScene.add(BizUploadScene.REPORT_IMAGE);
        supportedScene.add(BizUploadScene.REPORT_VIDEO);
        supportedScene.add(BizUploadScene.REPORT_VOICE);
        return supportedScene;
    }

    @Override
    protected List<String> getSupportedContentType() {
        switch (scene) {
            case AVATAR:
            case ID_CARD:
            case FAMILY_CARD:
            case REPORT_IMAGE:
                return imageTypes;
        }
        return new ArrayList<>();
    }
}