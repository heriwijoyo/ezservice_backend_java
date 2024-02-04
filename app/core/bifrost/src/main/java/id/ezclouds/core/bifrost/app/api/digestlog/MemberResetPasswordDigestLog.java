/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.biz.arahindonesia.model.authentication.BizMemberCommonSession;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberResetPasswordRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUpdatePasswordDigestLog.java, v 0.1 2024‐02‐04 8:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberResetPasswordDigestLog extends BaseDigestLog<BizMemberCommonSession> {

    public MemberResetPasswordDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<BizMemberCommonSession> result) {
        String requestData;
        if (request instanceof MemberResetPasswordRequest) {
            MemberResetPasswordRequest req = (MemberResetPasswordRequest) request;
            requestData = "loginType=" + req.getLoginType() + ",loginId=" + req.getLoginId();
        } else {
            requestData = "NULL";
        }

        setDigestMessage("request(" + requestData + ")");
        setErrorMessage(getErrorMessage(result));
    }
}