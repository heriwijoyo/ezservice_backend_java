/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api.handler;

import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.common.model.result.api.ApiResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizApiTemplateHandler.java, v 0.1 2024‐09‐28 5:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizApiTemplateHandler<T> {

    DigestLog composeDigestLog(ApiRequest request, ApiResult<T> result);
}