/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.request;

import id.ezclouds.common.model.request.BizRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizVerifyCommonSessionRequest.java, v 0.1 2024‐02‐05 9:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizVerifyCommonSessionRequest extends BizRequest {

    private String sessionId;
    private String scene;
    private String verifyStrategy;
    private String verifyCode;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getVerifyStrategy() {
        return verifyStrategy;
    }

    public void setVerifyStrategy(String verifyStrategy) {
        this.verifyStrategy = verifyStrategy;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}