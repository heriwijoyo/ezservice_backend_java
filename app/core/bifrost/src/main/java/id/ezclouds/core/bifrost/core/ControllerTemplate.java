/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.processor.ApiBizProcessor;
import id.ezclouds.core.bifrost.core.processor.BizProcessor;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ControllerTemplate.java, v 0.1 2023‐12‐09 1:01 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ControllerTemplate {

    private EzAppEvent appEvent;
    private BaseRequest baseRequest;
    private Handler handler;

    private PreBizProcessor preBizProcessor;
    private BizProcessor bizProcessor;

    private ControllerTemplate(EzAppEvent appEvent) {
        this.appEvent = appEvent;

        preBizProcessor = new PreBizProcessor();
        if (appEvent instanceof ApiEvent) {
            bizProcessor = new ApiBizProcessor();
        }
    }

    private void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    private void setHandler(Handler handler) {
        this.handler = handler;
    }

    public static ControllerTemplate withEvent(ApiEvent apiEvent) {
        return new ControllerTemplate(apiEvent);
    }

    public ControllerTemplate withRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
        return this;
    }

    public ControllerTemplate withHandler(Handler handler) {
        this.handler = handler;
        return this;
    }

    public void process() {
        EzAppContextHolder.init(appEvent);

        Object processResult = null;
        ErrorResult errorResult = null;

        try {
            AssertUtil.notNull(handler, EzErrorCode.SYSTEM_ERROR, "Undefined ControllerTemplate.Handler");

            preBizProcessor.process(appEvent, baseRequest);

            processResult = bizProcessor.process(appEvent, baseRequest);

        } catch (EzErrorException ezError) {
            errorResult = composeErrorResult(ezError);
        } catch (Exception exception) {

        } finally {
            //do logging
            //do rollback process if any

            if (errorResult != null) {
                handler.onError(errorResult);
                return;
            }

            handler.onResult(processResult);
        }
    }

    private ErrorResult composeErrorResult(EzErrorException ezError) {
        String errorMessage = ezError.getErrorMessage();
        if (errorMessage == null) {
            errorMessage = ezError.getEzErrorCode().getDescription();
        }

        ErrorResult errorResult = new ErrorResult();
        errorResult.setErrorCode(ezError.getEzErrorCode().getCode());
        errorResult.setErrorMessage(errorMessage);

        return errorResult;
    }

    public interface Handler {
        void onResult(Object result);
        void onError(ErrorResult errorResult);
    }
}