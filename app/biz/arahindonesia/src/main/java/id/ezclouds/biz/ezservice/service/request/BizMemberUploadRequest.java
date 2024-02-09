/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.biz.ezservice.constant.BizConstant;
import id.ezclouds.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUploadRequest.java, v 0.1 2024‐02‐07 3:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberUploadRequest extends BizMultipartRequest {

    private String scene;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    protected List<String> getSupportedContentType() {
        if (StringUtil.isNotBlank(scene)) {
            switch (scene) {
                case BizConstant.UploadScene.AVATAR:
                case BizConstant.UploadScene.ID_CARD:
                case BizConstant.UploadScene.FAMILY_CARD:
                case BizConstant.UploadScene.REPORT_IMAGE:
                    return imageTypes;
            }
        }
        return new ArrayList<>();
    }
}