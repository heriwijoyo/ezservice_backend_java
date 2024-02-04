/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.template;

import id.ezclouds.biz.arahindonesia.service.request.BizRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import jdk.internal.reflect.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizServiceTemplate.java, v 0.1 2023‐12‐31 1:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizServiceTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.APP_BIZ_SERVICE);

    public static void execute(BizRequest request, BizResult bizResult, Handler handler) {
        bizResult.setSuccess(false);

        String callerLocation = Reflection.getCallerClass().getSimpleName();

        try {
            handler.onRequestCheck();
            handler.onBizProcess();
        }
        catch (EzErrorException ezException) {
            bizResult.setSuccess(false);
            bizResult.setErrorCode(ezException.getEzErrorCode());
            bizResult.setErrorMessage(ezException.getErrorMessage());
            bizResult.setErrorLocation(callerLocation);
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(ezException));
        } catch (DataIntegrityViolationException exception) {
            bizResult.setSuccess(false);
            bizResult.setErrorCode(EzErrorCode.IDEMPOTENT_ERROR);
            bizResult.setErrorMessage(EzErrorCode.IDEMPOTENT_ERROR.getDescription());
            bizResult.setErrorLocation(callerLocation);
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
        } catch (Exception exception) {
            bizResult.setSuccess(false);
            bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
            bizResult.setErrorMessage(EzErrorCode.SYSTEM_ERROR.getDescription());
            bizResult.setErrorLocation(callerLocation);
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
        }
        finally {
            logRequest(request);
            logResult(bizResult);
        }
    }

    private static void logRequest(BizRequest request) {
        String traceId = EzAppContextHolder.getContext().getTraceId();
        String requestLog = request != null ? request.toString() : "BizRequest=NULL";
        LOGGER.info(traceId + " --- " + requestLog);
    }

    private static void logResult(BizResult result) {
        String traceId = EzAppContextHolder.getContext().getTraceId();
        LOGGER.info(traceId + " --- " + result.toString());
    }

    public interface Handler {
        void onRequestCheck() throws EzErrorException;
        void onBizProcess() throws EzErrorException;
    }
}