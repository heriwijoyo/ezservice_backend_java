/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app;

import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.ApiBizProcessor;
import id.ezclouds.core.bifrost.app.api.digestlog.CommonWebDigestLog;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiPageResult;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.app.web.WebBizProcessor;
import id.ezclouds.core.bifrost.app.web.event.WebEvent;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.core.bifrost.core.processor.WebProcessor;
import id.ezclouds.core.bifrost.core.util.ErrorResultUtil;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppController.java, v 0.1 2024‐01‐14 8:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class AppController {

    private PreBizProcessor preBizProcessor;

    protected abstract Logger getLogger();

    protected <T> ApiResult<T> executeInTemplate(ApiEvent apiEvent, ApiRequest apiRequest, RequestHandler<T> handler) {
        return executeInTemplate(apiEvent, apiRequest, null, handler);
    }

    protected <T> ApiResult<T> executeInTemplate(ApiEvent apiEvent, ApiRequest apiRequest, MultipartFile file, RequestHandler<T> handler) {
        return executeInTemplate(false, apiEvent, apiRequest, file, handler);
    }

    protected <T> ApiPageResult<T> executePageInTemplate(ApiEvent apiEvent, ApiRequest apiRequest, RequestHandler<T> handler) {
        return (ApiPageResult<T>) executeInTemplate(true, apiEvent, apiRequest, null, handler);
    }

    protected <T> ApiResult<T> executeInTemplate(boolean isPageRequest, ApiEvent apiEvent, ApiRequest apiRequest, MultipartFile file, RequestHandler<T> handler) {

        EzAppContextHolder.init(apiEvent);
        ApiResult<T> apiResult = new ApiResult<>();
        ApiPageResult<T> apiPageResult = new ApiPageResult<>();

        try {
            AssertUtil.notNull(apiEvent, EzErrorCode.ILLEGAL_ACTION, "Illegal action request");
            AssertUtil.notNull(apiRequest, EzErrorCode.ILLEGAL_PARAM, "Request could not be null");

            if (preBizProcessor == null) {
                preBizProcessor = SpringContextConfig.getBean(PreBizProcessor.class);
            }

            preBizProcessor.process(apiEvent, apiRequest);
            BizProcessor bizProcessor = SpringContextConfig.getBean(ApiBizProcessor.class);
            BizResult bizResult = bizProcessor.process(apiEvent, apiRequest, file);

            apiResult.setSuccess(bizResult.isSuccess());
            apiPageResult.setSuccess(bizResult.isSuccess());
            if (bizResult.isSuccess()) {
                if (isPageRequest) {
                    apiPageResult.setBizPageInfo(bizResult.getBizPageInfo());
                } else {
                    apiResult.setData(handler.convertResult(bizResult.getObject()));
                }
            } else {
                apiResult.setErrorResult(ErrorResultUtil.composeErrorResult(bizResult));
                apiPageResult.setErrorResult(ErrorResultUtil.composeErrorResult(bizResult));
            }

        } catch (Exception exception) {
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            apiResult.setErrorResult(ErrorResultUtil.composeErrorResult(exception));
            apiPageResult.setErrorResult(ErrorResultUtil.composeErrorResult(exception));
        } finally {
            DigestLog digestLog = handler.composeDigestLog(apiRequest, apiResult);
            if (isPageRequest) {
                digestLog = handler.composeDigestLog(apiRequest, apiPageResult);
            }
            DigestLogUtil.logDigest(getLogger(), digestLog);
        }

        if (apiResult.getErrorResult() != null) {
            apiResult.getErrorResult().setErrorContext(null);
            apiPageResult.getErrorResult().setErrorContext(null);
        }
        apiResult.setTimestamp(DateUtil.getCurrentFormattedDate());
        apiPageResult.setTimestamp(DateUtil.getCurrentFormattedDate());

        if (isPageRequest) {
            return apiPageResult;
        }
        return apiResult;
    }

    protected <T> T executeWebTemplate(WebEvent webEvent, Object request, HttpServletResponse servletResponse, WebRequestHandler<T> handler) {
        EzAppContextHolder.init(webEvent);
        T returnObject = null;

        ErrorResult errorResult = null;
        try {
            WebProcessor webProcessor = SpringContextConfig.getBean(WebBizProcessor.class);
            Object result = webProcessor.process(webEvent, request, servletResponse);
            returnObject = handler.convertResult(result);
        } catch (Exception exception) {
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            errorResult = ErrorResultUtil.composeErrorResult(exception);
            handler.onException(exception);
        } finally {
            boolean success = errorResult == null;
            String resultCode = success ? "RESULT_SUCCESS" : errorResult.getErrorCode();
            CommonWebDigestLog webDigestLog = new CommonWebDigestLog(success, resultCode);
            webDigestLog.setDigestMessage(handler.composeDigestLog());
            webDigestLog.setErrorMessage(errorResult);
            DigestLogUtil.logWebDigest(getLogger(), webDigestLog);
        }

        return returnObject;
    }

    public interface RequestHandler<T> {
        T convertResult(Object resultObject);
        DigestLog composeDigestLog(ApiRequest request, ApiResult<T> result);
    }

    public interface WebRequestHandler<T> {
        T convertResult(Object resultObject);
        void onException(Exception exception);
        String composeDigestLog();
    }
}