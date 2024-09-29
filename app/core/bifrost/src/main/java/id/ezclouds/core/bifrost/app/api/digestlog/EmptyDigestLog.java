/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.VerifyCommonSessionRequest;
import id.ezclouds.common.model.result.api.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EmptyDigestLog.java, v 0.1 2024‐02‐01 1:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EmptyDigestLog extends BaseDigestLog<Void> {

    public EmptyDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<Void> result) {
        String requestData = "NULL";

        if (request instanceof VerifyCommonSessionRequest) {
            VerifyCommonSessionRequest req = (VerifyCommonSessionRequest) request;
            requestData = "sessionId=" + req.getSessionId() + ",scene=" + req.getScene() + ",verifyStrategy=" + req.getVerifyStrategy() + ",verifyCode=" + req.getVerifyCode();
        }

        setDigestMessage("request(" + requestData + ")");
        setErrorMessage(getErrorMessage(result));
    }
}