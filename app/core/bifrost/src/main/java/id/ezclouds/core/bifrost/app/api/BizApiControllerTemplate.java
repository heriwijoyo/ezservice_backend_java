/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.common.facade.auth.AuthAppClientService;
import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.model.result.api.ErrorResult;
import id.ezclouds.common.model.util.ErrorResultUtil;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.common.model.request.api.ApiEvent;
import id.ezclouds.core.bifrost.app.api.handler.BizApiTemplateHandler;
import id.ezclouds.common.model.request.api.ApiRequest;
import id.ezclouds.common.model.result.api.ApiResult;
import id.ezclouds.core.bifrost.app.api.processor.ApiProcessor;
import id.ezclouds.core.shared.util.DigestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizApiControllerTemplate.java, v 0.1 2024‐09‐28 5:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class BizApiControllerTemplate {

    public static <T> ApiResult<T> execute(ApiEvent apiEvent, ApiRequest request, BizApiTemplateHandler<T> handler) {
        EzAppContextHolder.init(apiEvent);

        final ApiResult<T> result = new ApiResult<>();

        try {
            // request validation
            AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notNull(request.getAppClient(), EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(request.getAppClient().getOrganizationId(), EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(request.getAppClient().getApplicationId(), EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(request.getAppClient().getClientId(), EzErrorCode.ILLEGAL_PARAM);
            AssertUtil.notBlank(request.getAppClient().getClientSecret(), EzErrorCode.ILLEGAL_PARAM);

            // compose authAppClient
            AuthAppClient authAppClient = new AuthAppClient();
            authAppClient.setOrgId(request.getAppClient().getOrganizationId());
            authAppClient.setAppId(request.getAppClient().getApplicationId());
            authAppClient.setClientId(request.getAppClient().getClientId());
            authAppClient.setClientSecret(request.getAppClient().getClientSecret());

            // authorize app client
            boolean appClientAuthorized = BeanFacadeUtil
                    .getBean(AuthAppClientService.class)
                    .authorizeAppClient(authAppClient);
            AssertUtil.isTrue(appClientAuthorized, EzErrorCode.UNAUTHORIZED);
            EzAppContextHolder.getContext().setOrgId(authAppClient.getOrgId());
            if (request.getAppSession() != null) {
                EzAppContextHolder.getContext().setMemberSessionId(request.getAppSession().getMemberSessionId());
                EzAppContextHolder.getContext().setDeviceId(request.getAppSession().getDeviceId());
            }

            // process request
            BizResult bizResult = BeanFacadeUtil
                    .getBean(ApiProcessor.class)
                    .process(apiEvent, request);
            AssertUtil.notNull(bizResult, EzErrorCode.BIZ_PROCESS_ERROR);
            System.out.println(bizResult);

            result.setSuccess(bizResult.isSuccess());
            if (bizResult.isSuccess()) {
                AssertUtil.notNull(bizResult.getObject(), EzErrorCode.BIZ_PROCESS_ERROR);
                result.setData((T) bizResult.getObject());
            } else {
                result.setErrorResult(ErrorResultUtil.composeErrorResult(bizResult));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            EzAppContextHolder.getContext().appendErrorStackTrace(ExceptionUtil.getStackTrace(exception));
            ErrorResult errorResult = ErrorResultUtil.composeErrorResult(exception);
            result.setErrorResult(errorResult);
        } finally {
            DigestLog digestLog = handler.composeDigestLog(request, result);
            DigestLogUtil.logDigest(getLogger(apiEvent), digestLog);
        }

        result.setTimestamp(DateUtil.getCurrentFormattedDateMillis());
        return result;
    }

    private static Logger getLogger(ApiEvent apiEvent) {
        return LoggerFactory.getLogger(apiEvent.getLogger());
    }
}