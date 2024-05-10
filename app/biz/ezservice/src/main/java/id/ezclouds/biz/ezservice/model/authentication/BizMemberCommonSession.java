/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.model.authentication;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberCommonSession.java, v 0.1 2024‐02‐05 12:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberCommonSession {

    private String sessionId;
    private String scene;
    private String verifyStrategy;
    private String verifyTarget;

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

    public String getVerifyTarget() {
        return verifyTarget;
    }

    public void setVerifyTarget(String verifyTarget) {
        this.verifyTarget = verifyTarget;
    }
}