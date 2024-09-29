/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.model.result.api.ErrorResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ErrorResultUtil.java, v 0.1 2024‐05‐19 2:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ErrorResultUtil {

    public static ErrorResult composeErrorResult(BizResult bizResult) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(bizResult.getErrorCode().getCode());
        errorResult.setErrorContext(
                StringUtil.concateStrings(
                        bizResult.getErrorCode().getCode(),
                        "@",
                        bizResult.getErrorLocation())
        );
        errorResult.setErrorMessage(bizResult.getErrorMessage());
        return errorResult;
    }

    public static ErrorResult composeErrorResult(Exception exception) {
        if (exception instanceof EzErrorException) {
            return getErrorResult((EzErrorException) exception);
        }
        return getErrorResult(new EzErrorException(EzErrorCode.SYSTEM_ERROR, EzErrorCode.SYSTEM_ERROR.getDescription()));
    }

    private static ErrorResult getErrorResult(EzErrorException ezErrorException) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(ezErrorException.getEzErrorCode().getCode());
        errorResult.setErrorContext(ezErrorException.getEzErrorCode().getDescription());
        errorResult.setErrorMessage(ezErrorException.getErrorMessage() != null ? ezErrorException.getErrorMessage() : ezErrorException.getEzErrorCode().getDescription());
        return errorResult;
    }
}