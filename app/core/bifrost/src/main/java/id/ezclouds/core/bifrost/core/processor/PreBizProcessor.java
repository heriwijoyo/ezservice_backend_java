/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.election.service.apibiz.BizAuthService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.common.model.request.api.ApiEvent;
import id.ezclouds.common.model.request.api.ApiRequest;
import id.ezclouds.common.model.request.api.RequestAppClient;
import id.ezclouds.common.util.context.EzAppContextHolder;
import id.ezclouds.common.util.context.EzAppEvent;
import id.ezclouds.common.model.core.CoreOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PreBizProcessor.java, v 0.1 2023‐12‐09 3:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class PreBizProcessor {

    @Autowired
    private BizAuthService bizAuthService;

    public void process(EzAppEvent event, ApiRequest apiRequest) throws EzErrorException, ClassCastException {

        if (event instanceof ApiEvent) {
            RequestAppClient reqClient = apiRequest.getAppClient();
            AssertUtil.notNull(reqClient, EzErrorCode.ILLEGAL_PARAM, "Request.AuthAppClient is null");
            AssertUtil.notBlank(reqClient.getOrganizationId(), EzErrorCode.ILLEGAL_PARAM, "Request.AuthAppClient.organizationId is blank");
            AssertUtil.notBlank(reqClient.getApplicationId(), EzErrorCode.ILLEGAL_PARAM, "Request.AuthAppClient.applicationId is blank");
            AssertUtil.notBlank(reqClient.getClientId(), EzErrorCode.ILLEGAL_PARAM, "Request.AuthAppClient.clientId is blank");
            AssertUtil.notBlank(reqClient.getClientSecret(), EzErrorCode.ILLEGAL_PARAM, "Request.AuthAppClient.clientSecret is blank");

            CoreAuthResult<CoreOrganization> clientAuthResult = bizAuthService.authAppClient(
                    reqClient.getOrganizationId(),
                    reqClient.getApplicationId(),
                    reqClient.getClientId(),
                    reqClient.getClientSecret()
            );
            AssertUtil.isTrue(clientAuthResult.isSuccess(), EzErrorCode.UNAUTHORIZED, "Unauthorized client request");
            AssertUtil.notNull(clientAuthResult.getData(), EzErrorCode.UNAUTHORIZED, "Unauthorized client request");

            EzAppContextHolder.getContext().setOrgId(reqClient.getOrganizationId());
            EzAppContextHolder.getContext().setOrgCode(clientAuthResult.getData().getCode());
            EzAppContextHolder.getContext().setOrgExtendConfig(clientAuthResult.getData().getExtendInfo());
            EzAppContextHolder.getContext().setAppId(reqClient.getApplicationId());
            EzAppContextHolder.getContext().setDeviceId(reqClient.getDeviceId());
            EzAppContextHolder.getContext().setAppVersionNo(reqClient.getAppVersionNo());

            if (apiRequest.getAppSession() != null) {
                EzAppContextHolder.getContext().setMemberSessionId(apiRequest.getAppSession().getMemberSessionId());
            }
        }
    }
}