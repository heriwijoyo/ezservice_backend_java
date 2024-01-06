/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiControllerTemplate.java, v 0.1 2024‐01‐06 3:55 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class ApiControllerTemplate<T> {

    private EzAppEvent ezAppEvent;
    private ApiResult<T> apiResult;


    public ApiControllerTemplate(EzAppEvent ezAppEvent) {
        this.ezAppEvent = ezAppEvent;
        this.apiResult = new ApiResult<>();
    }

    public ApiResult<T> execute(Handler<T> handler) {

        EzAppContextHolder.init(ezAppEvent);


        return apiResult;
    }

    public interface Handler<T> {
        BizResult<T> onApiProcess();
    }
}