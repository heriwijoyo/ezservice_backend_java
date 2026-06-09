/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.request;

import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncTriggerRequest.java, v 0.1 2024‐05‐10 11:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizAsyncTriggerRequest extends BizRequest {

    private String scene;
    private String targetId;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}