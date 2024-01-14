/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import id.ezclouds.core.bifrost.core.processor.BizProcessorFactory;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppController.java, v 0.1 2024‐01‐14 8:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    private PreBizProcessor preBizProcessor;

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
            ezException.printStackTrace();
            apiResult.setErrorResult(composeErrorResult(ezException));
        } catch (Exception exception) {
            exception.printStackTrace();
            apiResult.setErrorResult(composeErrorResult());
        } finally {
            logExecution(apiRequest, apiResult);
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
        errorResult.setErrorMessage(ezException.getErrorMessage());
        return errorResult;
    }

    private ErrorResult composeErrorResult() {
        EzErrorException ezErrorException = new EzErrorException(
                EzErrorCode.SYSTEM_ERROR,
                EzErrorCode.SYSTEM_ERROR.getDescription()
        );
        return composeErrorResult(ezErrorException);
    }

    private <T> void logExecution(ApiRequest request, ApiResult<T> apiResult) {
        StringBuilder stringBuilder = new StringBuilder();

        String traceId = EzAppContextHolder.getContext().getTraceId();
        EzAppEvent ezAppEvent = EzAppContextHolder.getContext().getEzAppEvent();
        if (ezAppEvent == null) {
            ezAppEvent = ApiEvent.UNKNOWN_EVENT;
        }
        String eventCode = ezAppEvent.getEventCode();
        String resultStatus = apiResult.isSuccess() ? "Y" : "N";
        String errorCode = "";
        String errorMessage = "";
        if (apiResult.getErrorResult() != null) {
            errorCode = apiResult.getErrorResult().getErrorCode();
            errorMessage = StringUtil.concateStrings(
                    apiResult.getErrorResult().getErrorMessage(),
                    "@",
                    apiResult.getErrorResult().getErrorContext()
            );
        }

        stringBuilder.append("[");
        stringBuilder.append(traceId);
        stringBuilder.append(",");
        stringBuilder.append(eventCode);
        stringBuilder.append(",");
        stringBuilder.append(resultStatus);
        stringBuilder.append(",");
        stringBuilder.append(errorCode);
        stringBuilder.append("][");
        stringBuilder.append(errorMessage);
        stringBuilder.append("][");
        stringBuilder.append(composeRequestLog(ezAppEvent, request));
        stringBuilder.append(",");
        stringBuilder.append(composeResultLog(ezAppEvent, apiResult));
        stringBuilder.append("]");

        LOGGER.info(stringBuilder.toString());
    }

    private String composeRequestLog(EzAppEvent ezAppEvent, ApiRequest apiRequest) {
        return apiRequest.toString();
//        if (ezAppEvent instanceof ApiEvent) {
//            ApiEvent apiEvent = (ApiEvent) ezAppEvent;
//            switch (apiEvent) {
//                case API_APP_SETTING:
//                    return "";
//
//                default:
//                    return "";
//            }
//        }
//        return "";
    }

    private <T> String composeResultLog(EzAppEvent ezAppEvent, ApiResult<T> apiResult) {
        if (ezAppEvent instanceof ApiEvent) {
            ApiEvent apiEvent = (ApiEvent) ezAppEvent;
            switch (apiEvent) {
                case API_APP_SETTING:
                    return "";

                default:
                    return "";
            }
        }
        return "";
    }

    interface RequestHandler<T> {
        T convertResult(Object resultObject);
    }
}