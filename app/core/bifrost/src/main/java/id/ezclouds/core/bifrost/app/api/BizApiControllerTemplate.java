/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.context.EzAppEvent;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.bifrost.app.api.handler.BizApiTemplateHandler;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;

import java.lang.reflect.ParameterizedType;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizApiControllerTemplate.java, v 0.1 2024‐09‐28 5:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizApiControllerTemplate {

    public static <T> ApiResult<T> execute(EzAppEvent appEvent, ApiRequest request, BizApiTemplateHandler handler) {
        final ApiResult<T> apiResult = new ApiResult<>();

        try {


        } catch (Exception e) {

        } finally {

        }

        return apiResult;
    }
}