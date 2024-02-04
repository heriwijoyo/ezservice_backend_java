/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.result;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthMemberCommonSessionInfo.java, v 0.1 2024‐02‐05 12:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class CoreAuthMemberCommonSessionInfo {

    private String sessionId;
    private String scene;
    private String verifyStrategy;
    private String verifyTarget;
    private String verifyCode;
    private String expiryTime;

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }
}