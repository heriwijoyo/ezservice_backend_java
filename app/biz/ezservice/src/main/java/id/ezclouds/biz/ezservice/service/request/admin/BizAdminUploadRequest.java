/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request.admin;

import id.ezclouds.biz.ezservice.enums.BizUploadScene;
import id.ezclouds.biz.ezservice.service.request.BizMultipartRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminUploadRequest.java, v 0.1 2024‐02‐12 9:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAdminUploadRequest extends BizMultipartRequest {

    private String sessionId;
    private BizUploadScene scene;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setScene(BizUploadScene scene) {
        this.scene = scene;
    }

    @Override
    public BizUploadScene getScene() {
        return scene;
    }

    @Override
    protected void presetScene(BizUploadScene scene) {
        switch (scene) {
            case ADMIN_NEWS_GALLERY_UPDATE:
            case ADMIN_EVENT_GALLERY_UPDATE:
                setMultipartFileOptional();
                break;
        }
    }

    @Override
    protected List<BizUploadScene> getSupportedScene() {
        List<BizUploadScene> supportedScene = new ArrayList<>();
        supportedScene.add(BizUploadScene.ADMIN_APP_BUILD_PACKAGE);
        supportedScene.add(BizUploadScene.ADMIN_APP_ICON);
        supportedScene.add(BizUploadScene.ADMIN_APP_GALLERY);
        supportedScene.add(BizUploadScene.ADMIN_NEWS_GALLERY);
        supportedScene.add(BizUploadScene.ADMIN_NEWS_GALLERY_UPDATE);
        supportedScene.add(BizUploadScene.ADMIN_EVENT_GALLERY);
        supportedScene.add(BizUploadScene.ADMIN_EVENT_GALLERY_UPDATE);
        supportedScene.add(BizUploadScene.ADMIN_VIDEO_CARD_GALLERY);
        supportedScene.add(BizUploadScene.ADMIN_DOCS_GALLERY);
        return supportedScene;
    }

    @Override
    protected List<String> getSupportedContentType() {
        switch (scene) {
            case ADMIN_APP_GALLERY:
            case ADMIN_NEWS_GALLERY:
            case ADMIN_EVENT_GALLERY:
            case ADMIN_VIDEO_CARD_GALLERY:
                return imageTypes;
            case ADMIN_APP_ICON:
                return onlyPNG;
            case ADMIN_APP_BUILD_PACKAGE:
                return onlyAPK;
            default:
                return new ArrayList<>();
        }
    }
}