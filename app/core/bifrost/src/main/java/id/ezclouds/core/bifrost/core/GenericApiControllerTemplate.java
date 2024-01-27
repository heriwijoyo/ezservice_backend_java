/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core;

import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.bifrost.app.api.result.ApiResult;
import id.ezclouds.core.shared.context.EzAppEvent;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: GenericApiControllerTemplate.java, v 0.1 2024‐01‐14 8:04 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class GenericApiControllerTemplate {

    public static <T>void execute(EzAppEvent ezAppEvent, ApiResult<T> apiResult) {

        try {
            AssertUtil.notNull(ezAppEvent, EzErrorCode.ILLEGAL_ACTION, "Illegal action request");


        } catch (Exception exception) {

        }
    }
}