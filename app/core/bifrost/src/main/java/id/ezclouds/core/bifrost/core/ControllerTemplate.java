/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.app.api.result.ErrorResult;
import id.ezclouds.core.bifrost.core.processor.BizProcessorFactory;
import id.ezclouds.core.bifrost.core.processor.PreBizProcessor;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ControllerTemplate.java, v 0.1 2023‐12‐09 1:01 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
@Scope(value = "prototype")
public class ControllerTemplate {

    private EzAppEvent appEvent;
    private BaseRequest baseRequest;

    @Autowired
    private PreBizProcessor preBizProcessor;

    @Autowired
    private BizProcessorFactory bizProcessorFactory;

    public void setAppEvent(EzAppEvent appEvent) {
        this.appEvent = appEvent;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public void process(Handler handler) {

        EzAppContextHolder.init(appEvent);

        Object processResult = null;
        ErrorResult errorResult = null;

        try {
            AssertUtil.notNull(appEvent, EzErrorCode.SYSTEM_ERROR, "Undefined appEvent");

            preBizProcessor.process(appEvent, baseRequest);

            processResult = bizProcessorFactory.getBizProcessor(appEvent).process(appEvent, baseRequest);

        } catch (EzErrorException ezError) {
            errorResult = composeErrorResult(ezError);
        } catch (Exception exception) {
            errorResult = composeErrorResult(new EzErrorException(EzErrorCode.SYSTEM_ERROR, "System unknown exception: ", exception.getMessage()));
        } finally {
            //do logging
            //do rollback process if any


            if (errorResult != null) {
                handler.onError(errorResult);
            } else {
                handler.onResult(processResult);
            }
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
        errorResult.setErrorContext(ezError.getEzErrorCode().getInnerCode());

        return errorResult;
    }

    public interface Handler {
        void onResult(Object result);
        void onError(ErrorResult errorResult);
    }
}