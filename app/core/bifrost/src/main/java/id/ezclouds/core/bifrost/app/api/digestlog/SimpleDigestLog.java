/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberUploadRequest;
import id.ezclouds.core.bifrost.app.api.request.VerifyCommonSessionRequest;
import id.ezclouds.common.model.result.api.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SimpleDigestLog.java, v 0.1 2024‐01‐28 3:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class SimpleDigestLog extends BaseDigestLog<String> {

    public SimpleDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<String> result) {
        String requestData = "NULL";
        if (request instanceof VerifyCommonSessionRequest) {
            VerifyCommonSessionRequest vRequest = (VerifyCommonSessionRequest) request;
            requestData =   "sessionId=" + vRequest.getSessionId() +
                            ",scene=" + vRequest.getScene() +
                            ",verifyStrategy=" + vRequest.getVerifyStrategy() +
                            ",verifyCode" + vRequest.getVerifyCode();
        }

        if (request instanceof MemberUploadRequest) {
            MemberUploadRequest mRequest = (MemberUploadRequest) request;
            requestData = "scene=" + mRequest.getScene();
        }

        setDigestMessage("request(" + requestData + ")");
        setErrorMessage(getErrorMessage(result));
    }
}