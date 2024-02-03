/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.biz.arahindonesia.service.result.BizMemberLoginResult;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberLoginRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberLoginDigestLog.java, v 0.1 2024‐01‐29 4:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberLoginDigestLog extends BaseDigestLog<BizMemberLoginResult> {

    public MemberLoginDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<BizMemberLoginResult> result) {
        String requestData = "request(";
        if (request instanceof MemberLoginRequest) {
            MemberLoginRequest loginRequest = (MemberLoginRequest) request;
            requestData += "loginType=" + loginRequest.getLoginType() + ",loginId=" + loginRequest.getLoginId() + ")";
        }
        setDigestMessage(requestData);
        setErrorMessage("errorContext=" + result.getErrorResult().getErrorContext());
    }
}