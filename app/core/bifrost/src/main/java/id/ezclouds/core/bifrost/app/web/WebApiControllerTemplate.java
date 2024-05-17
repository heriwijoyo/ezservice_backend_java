/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.web;

import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.core.shared.result.PageResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.digestlog.EmptyDigestLog;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.app.web.result.WebApiPageResult;
import id.ezclouds.core.bifrost.app.web.result.WebApiResult;
import id.ezclouds.core.shared.context.EzAppContextHolder;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebApiControllerTemplate.java, v 0.1 2024‐02‐11 8:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebApiControllerTemplate {

    public static <T> void execute(WebEvent webEvent, WebApiPageResult<T> apiResult, PageHandler<T> handler) {
        EzAppContextHolder.init(webEvent);
        BizResult bizResult = new BizResult();
        try {
            bizResult = handler.onProcess();
            apiResult.setSuccess(bizResult.isSuccess());

            if (bizResult.isSuccess()) {

                apiResult.setPageResult(handler.convertResult(bizResult.getObject()));
            } else {
                apiResult.setMessage(bizResult.getErrorMessage());
            }
        } catch (Exception exception) {
            if (exception instanceof EzErrorException) {
                bizResult.setErrorCode(((EzErrorException)exception).getEzErrorCode());
            } else {
                bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            }
        } finally {
            EmptyDigestLog digestLog = new EmptyDigestLog(bizResult.isSuccess(), getResultCode(bizResult));
            handler.onDigestLog(digestLog);
        }

        if (bizResult.getErrorCode() == EzErrorCode.SESSION_INVALID) {
            apiResult.setSessionExpired(true);
        }
    }

    public static <T> void execute(WebEvent webEvent, WebApiResult<T> apiResult, Handler<T> handler) {

        EzAppContextHolder.init(webEvent);
        BizResult bizResult = new BizResult();
        try {
            bizResult = handler.onProcess();
            apiResult.setSuccess(bizResult.isSuccess());

            if (bizResult.isSuccess()) {
                apiResult.setData(handler.convertResult(bizResult.getObject()));
            } else {
                apiResult.setMessage(bizResult.getErrorMessage());
            }
        } catch (Exception exception) {
            if (exception instanceof EzErrorException) {
                bizResult.setErrorCode(((EzErrorException)exception).getEzErrorCode());
            } else {
                bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            }
        } finally {
            EmptyDigestLog digestLog = new EmptyDigestLog(bizResult.isSuccess(), getResultCode(bizResult));
            handler.onDigestLog(digestLog);
        }

        if (bizResult.getErrorCode() == EzErrorCode.SESSION_INVALID) {
            apiResult.setSessionExpired(true);
        }
    }

    private static String getResultCode(BizResult bizResult) {
        if (bizResult.isSuccess()) {
            return "RESULT_SUCCESS";
        }
        if (bizResult.getErrorCode() != null) {
            return bizResult.getErrorCode().getCode();
        }
        return "UNKNOWN_RESULT";
    }

    interface Handler<T> {
        BizResult onProcess() throws Exception;
        T convertResult(Object object);
        void onDigestLog(DigestLog digestLog);
    }

    interface PageHandler<T> {
        BizResult onProcess() throws Exception;
        PageResult<T> convertResult(Object object);
        void onDigestLog(DigestLog digestLog);
    }
}