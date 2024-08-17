/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.request;

import id.ezclouds.common.model.request.BizRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberLoginRequest.java, v 0.1 2024‐01‐29 1:03 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberResetPasswordRequest extends BizRequest {

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}