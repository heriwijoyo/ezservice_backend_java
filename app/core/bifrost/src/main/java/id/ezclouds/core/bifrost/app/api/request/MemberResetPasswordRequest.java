/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.request;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberLoginRequest.java, v 0.1 2023‐12‐11 1:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberResetPasswordRequest extends ApiRequest {

    private String loginType;
    private String loginId;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}