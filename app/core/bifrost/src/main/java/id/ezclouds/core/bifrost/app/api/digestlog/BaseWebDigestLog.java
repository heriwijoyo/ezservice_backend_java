/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.digestlog;

import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.common.model.result.api.ErrorResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BaseDigestLog.java, v 0.1 2024‐01‐28 4:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class BaseWebDigestLog extends DigestLog {

    public BaseWebDigestLog(boolean success, String resultCode) {
        super(success, resultCode);
    }

    public abstract void composeDigestMessage(String message);

    public void setErrorMessage(ErrorResult errorResult) {
        String errorContext;
        if (errorResult == null) {
            errorContext = "NULL";
        } else {
            errorContext = errorResult.getErrorMessage();
        }
        setErrorMessage(errorContext);
    }
}