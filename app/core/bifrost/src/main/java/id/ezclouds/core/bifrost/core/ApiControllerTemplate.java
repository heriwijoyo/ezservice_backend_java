/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import id.ezclouds.core.bifrost.core.processor.BizProcessorFactory;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiControllerTemplate.java, v 0.1 2024‐01‐06 3:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiControllerTemplate<T> {

    private EzAppEvent ezAppEvent;
    private ApiResult<T> apiResult;

    @Autowired
    private PreBizProcessor preBizProcessor;

    public ApiControllerTemplate(EzAppEvent ezAppEvent) {
        this.ezAppEvent = ezAppEvent;
        this.apiResult = new ApiResult<>();

        preBizProcessor = SpringContextConfig.getBean(PreBizProcessor.class);
    }

    public ApiResult<T> execute(ApiRequest request, HttpServletResponse response, ConvertHandler<T> convertHandler) {

        try {
            AssertUtil.notNull(ezAppEvent, EzErrorCode.ILLEGAL_ACTION, "Illegal action request");
            EzAppContextHolder.init(ezAppEvent);

            preBizProcessor.process(ezAppEvent, request);

            BizProcessor bizProcessor = BizProcessorFactory.getBizProcessor(ezAppEvent);
            BizResult bizResult = bizProcessor.process(ezAppEvent, request);

            apiResult.setSuccess(bizResult.isSuccess());
            apiResult.setData(convertHandler.convertFrom(bizResult.getObject()));
        } catch (EzErrorException ezError) {
            ezError.printStackTrace();
            apiResult.setErrorResult(composeErrorResult(ezError));
        } catch (Exception exception) {
            exception.printStackTrace();
            apiResult.setErrorResult(composeExceptionErrorResult());
        } finally {

            //TODO: add logging logic

            if (!apiResult.isSuccess()) {
                setErrorResult(apiResult, response);
            }
        }

        return apiResult;
    }

    private ErrorResult composeErrorResult(EzErrorException ezError) {
        String errorMessage = ezError.getErrorMessage();
        if (errorMessage == null) {
            errorMessage = ezError.getEzErrorCode().getDescription();
        }

        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(ezError.getEzErrorCode().getCode());
        errorResult.setErrorMessage(errorMessage);
        errorResult.setErrorContext(ezError.getEzErrorCode().getInnerCode());

        return errorResult;
    }

    private ErrorResult composeExceptionErrorResult() {
        EzErrorCode ezErrorCode = EzErrorCode.SYSTEM_ERROR;

        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(ezErrorCode.getCode());
        errorResult.setErrorMessage(ezErrorCode.getDescription());
        errorResult.setErrorContext(ezErrorCode.getInnerCode());

        return errorResult;
    }

    private void setErrorResult(ApiResult<T> apiResult, HttpServletResponse response) {
        if (EzErrorCode.UNAUTHORIZED.getCode().equals(apiResult.getErrorResult().getErrorCode())) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        if (EzErrorCode.SESSION_EXPIRED.getCode().equals(apiResult.getErrorResult().getErrorCode())) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    public interface ConvertHandler<T> {
        T convertFrom(Object object);
    }
}