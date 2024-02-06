/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BaseDigestLog.java, v 0.1 2024‐01‐28 4:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class BaseDigestLog<T> extends DigestLog {

    public BaseDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    public abstract void composeDigest(ApiRequest request, ApiResult<T> result);

    protected String getErrorMessage(ApiResult<T> result) {
        String errorContext;
        if (result.getErrorResult() == null) {
            errorContext = "NULL";
        } else {
            errorContext = result.getErrorResult().getErrorContext();
        }
        return errorContext;
    }
}