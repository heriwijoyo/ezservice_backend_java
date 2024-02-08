/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import id.ezclouds.core.bifrost.core.processor.BizProcessorFactory;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppController.java, v 0.1 2024‐01‐14 8:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class AppController {

    private PreBizProcessor preBizProcessor;

    protected abstract Logger getLogger();

    @RequestMapping(value = "/")
    private String getIndexPage() {
        return "Welcome to Arah Indonesia";
    }

    protected <T> ApiResult<T> executeInTemplate(EzAppEvent ezAppEvent, ApiRequest apiRequest, RequestHandler<T> handler) {

        EzAppContextHolder.init(ezAppEvent);
        ApiResult<T> apiResult = new ApiResult<>();

        try {
            AssertUtil.notNull(ezAppEvent, EzErrorCode.ILLEGAL_ACTION, "Illegal action request");
            AssertUtil.notNull(apiRequest, EzErrorCode.ILLEGAL_PARAM, "Request could not be null");

            if (preBizProcessor == null) {
                preBizProcessor = SpringContextConfig.getBean(PreBizProcessor.class);
            }

            preBizProcessor.process(ezAppEvent, apiRequest);
            BizProcessor bizProcessor = BizProcessorFactory.getBizProcessor(ezAppEvent);
            BizResult bizResult = bizProcessor.process(ezAppEvent, apiRequest);

            apiResult.setSuccess(bizResult.isSuccess());
            if (bizResult.isSuccess()) {
                apiResult.setData(handler.convertResult(bizResult.getObject()));
            } else {
                apiResult.setErrorResult(composeErrorResult(bizResult));
            }

        } catch (EzErrorException ezException) {
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(ezException));
            apiResult.setErrorResult(composeErrorResult(ezException));
        } catch (Exception exception) {
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            apiResult.setErrorResult(composeErrorResult());
        } finally {
            DigestLog digestLog = handler.composeDigestLog(apiRequest, apiResult);
            DigestLogUtil.logDigest(getLogger(), digestLog);
        }

        return apiResult;
    }

    private ErrorResult composeErrorResult(BizResult bizResult) {
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

    private ErrorResult composeErrorResult(EzErrorException ezException) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(ezException.getEzErrorCode().getCode());
        errorResult.setErrorContext(ezException.getEzErrorCode().getDescription());
        errorResult.setErrorMessage(ezException.getErrorMessage() != null ? ezException.getErrorMessage() : ezException.getEzErrorCode().getDescription() );
        return errorResult;
    }

    private ErrorResult composeErrorResult() {
        EzErrorException ezErrorException = new EzErrorException(
                EzErrorCode.SYSTEM_ERROR,
                EzErrorCode.SYSTEM_ERROR.getDescription()
        );
        return composeErrorResult(ezErrorException);
    }

    interface RequestHandler<T> {
        T convertResult(Object resultObject);
        DigestLog composeDigestLog(ApiRequest request, ApiResult<T> result);
    }
}