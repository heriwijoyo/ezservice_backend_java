/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request.admin;

import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.biz.ezservice.service.request.BizMultipartRequest;
import id.ezclouds.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminUploadRequest.java, v 0.1 2024‐02‐12 9:21 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAdminUploadRequest extends BizMultipartRequest {

    private String sessionId;
    private String scene;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String getScene() {
        return scene;
    }

    @Override
    protected List<String> getSupportedScene() {
        List<String> supportedScene = new ArrayList<>();
        supportedScene.add(BizConstant.UploadScene.ADMIN_APP_GALLERY);
        supportedScene.add(BizConstant.UploadScene.ADMIN_NEWS_GALLERY);
        supportedScene.add(BizConstant.UploadScene.ADMIN_EVENT_GALLERY);
        supportedScene.add(BizConstant.UploadScene.ADMIN_VIDEO_CARD_GALLERY);
        supportedScene.add(BizConstant.UploadScene.ADMIN_OTHER_GALLERY);
        return supportedScene;
    }

    @Override
    protected List<String> getSupportedContentType() {
        if (StringUtil.isNotBlank(scene)) {
            switch (scene) {
                case BizConstant.UploadScene.ADMIN_APP_GALLERY:
                case BizConstant.UploadScene.ADMIN_NEWS_GALLERY:
                case BizConstant.UploadScene.ADMIN_EVENT_GALLERY:
                case BizConstant.UploadScene.ADMIN_VIDEO_CARD_GALLERY:
                    return imageTypes;
            }
        }
        return new ArrayList<>();
    }
}