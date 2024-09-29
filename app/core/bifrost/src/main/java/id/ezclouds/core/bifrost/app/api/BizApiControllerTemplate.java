/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.common.facade.auth.AuthAppClientService;
import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.facade.BeanFacadeUtil;
import id.ezclouds.common.util.logger.DigestLog;
import id.ezclouds.common.model.request.api.ApiEvent;
import id.ezclouds.core.bifrost.app.api.handler.BizApiTemplateHandler;
import id.ezclouds.common.model.request.api.ApiRequest;
import id.ezclouds.common.model.result.api.ApiResult;
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



        } catch (Exception e) {

        } finally {
            DigestLog digestLog = handler.composeDigestLog(request, result);
            DigestLogUtil.logDigest(getLogger(apiEvent), digestLog);

        }

        return result;
    }

    private static Logger getLogger(ApiEvent apiEvent) {
        return LoggerFactory.getLogger(apiEvent.getLogger());
    }
}