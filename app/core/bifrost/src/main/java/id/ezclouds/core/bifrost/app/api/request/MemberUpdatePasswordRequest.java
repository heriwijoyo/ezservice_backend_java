/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

import id.ezclouds.common.model.request.api.ApiRequest;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUpdatePasswordRequest.java, v 0.1 2024‐02‐04 8:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberUpdatePasswordRequest extends ApiRequest {

    private String mode;
    private String newPassword;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}