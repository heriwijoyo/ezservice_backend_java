/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.arahindonesia.service.apibiz.BizSampleService;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.SpringContextConfig;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import id.ezclouds.core.bifrost.core.processor.BizProcessorFactory;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.core.shared.context.EzAppContext;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;
import id.ezclouds.core.shared.model.CoreSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppController.java, v 0.1 2024‐01‐14 8:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.APP_CONTROLLER);

    private PreBizProcessor preBizProcessor;

    @Autowired
    private BizSampleService bizSampleService;

    @RequestMapping(value = "/")
    private String getIndexPage() {
        return "Welcome to Arah Indonesia";
    }

    @GetMapping(value = "/sample.php", consumes = {MediaType.ALL_VALUE})
    private String getSample() {
        CoreSample coreSample = bizSampleService.getCoreSample();
        if (coreSample == null) {
            LOGGER.info("CoreSample is NULL, return static value");
            return "Static Sample Response";
        }
        LOGGER.info("CoreSample is not NULL, return DB value");
        return coreSample.getValue();
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
            String digestLog = handler.composeDigestLog(apiRequest, apiResult);
            writeLog(apiResult, digestLog);
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

    private <T> void writeLog(ApiResult<T> apiResult, String digestLog) {
        StringBuilder stringBuilder = new StringBuilder();

        EzAppContext ezAppContext = EzAppContextHolder.getContext();
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
                    apiResult.getErrorResult().getErrorContext(),
                    "::",
                    apiResult.getErrorResult().getErrorMessage()
            );
        }

        stringBuilder.append(ezAppContext.getTraceId());
        stringBuilder.append(" - ");
        stringBuilder.append("[");
        stringBuilder.append(eventCode);
        stringBuilder.append(",");
        stringBuilder.append(ezAppContext.getTimeCost());
        stringBuilder.append(",");
        stringBuilder.append(resultStatus);
        stringBuilder.append(",");
        stringBuilder.append(errorCode);
        stringBuilder.append("][");
        stringBuilder.append(errorMessage);
        stringBuilder.append("][");
        stringBuilder.append(digestLog);
        stringBuilder.append("]");

        LOGGER.info(stringBuilder.toString());
    }

    interface RequestHandler<T> {
        T convertResult(Object resultObject);
        String composeDigestLog(ApiRequest request, ApiResult<T> result);
    }
}