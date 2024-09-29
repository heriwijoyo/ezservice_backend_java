/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberUpdatePasswordRequest;
import id.ezclouds.common.model.result.api.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberUpdatePasswordDigestLog.java, v 0.1 2024‐02‐04 8:46 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class MemberUpdatePasswordDigestLog extends BaseDigestLog<String> {

    public MemberUpdatePasswordDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    @Override
    public void composeDigest(ApiRequest request, ApiResult<String> result) {
        String requestData;
        if (request instanceof MemberUpdatePasswordRequest) {
            requestData = "mode=" + ((MemberUpdatePasswordRequest) request).getMode();
        } else {
            requestData = "NULL";
        }

        setDigestMessage("request(" + requestData + ")");
        setErrorMessage(getErrorMessage(result));
    }
}