/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.model.AppClient;
import id.ezclouds.biz.arahindonesia.service.data.AppClientService;
import id.ezclouds.biz.arahindonesia.service.data.OrganizationService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
import id.ezclouds.core.bifrost.app.api.request.RequestAppClient;
import id.ezclouds.core.bifrost.core.BaseRequest;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.context.EzAppEvent;
import id.ezclouds.core.shared.model.CoreOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PreBizProcessor.java, v 0.1 2023‐12‐09 3:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class PreBizProcessor {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AppClientService appClientService;

    public void process(EzAppEvent event, BaseRequest request) throws EzErrorException, ClassCastException {

        if (event instanceof ApiEvent) {
            AssertUtil.isTrue((request instanceof ApiRequest), EzErrorCode.ILLEGAL_PARAM, "Unsupported request type");

            ApiRequest apiRequest = (ApiRequest) request;
            RequestAppClient reqClient = apiRequest.getAppClient();
            AssertUtil.notNull(reqClient, EzErrorCode.ILLEGAL_PARAM, "Request.AppClient is null");
            AssertUtil.notBlank(reqClient.getOrganizationId(), EzErrorCode.ILLEGAL_PARAM, "Request.AppClient.organizationId is blank");
            AssertUtil.notBlank(reqClient.getApplicationId(), EzErrorCode.ILLEGAL_PARAM, "Request.AppClient.applicationId is blank");
            AssertUtil.notBlank(reqClient.getClientId(), EzErrorCode.ILLEGAL_PARAM, "Request.AppClient.clientId is blank");
            AssertUtil.notBlank(reqClient.getClientSecret(), EzErrorCode.ILLEGAL_PARAM, "Request.AppClient.clientSecret is blank");

            CoreOrganization coreOrganization = organizationService
                    .getOrganizations()
                    .stream()
                    .filter(org -> reqClient.getOrganizationId().equals(org.getOrgId()))
                    .findFirst()
                    .get();
            AssertUtil.notNull(coreOrganization, EzErrorCode.UNAUTHORIZED, "Unauthorized client request");

            AppClient appClient = appClientService
                    .getAppClients()
                    .stream()
                    .filter(aClient -> reqClient.getApplicationId().equals(aClient.getAppId()))
                    .filter(aClient -> reqClient.getClientId().equals(aClient.getClientId()))
                    .filter(aClient -> reqClient.getClientSecret().equals(aClient.getClientSecret()))
                    .findFirst()
                    .orElse(null);
            AssertUtil.notNull(appClient, EzErrorCode.UNAUTHORIZED, "Unauthorized client request");

            EzAppContextHolder.getContext().setOrgId(coreOrganization.getOrgId());
            EzAppContextHolder.getContext().setOrgCode(coreOrganization.getCode());
            EzAppContextHolder.getContext().setAppId(appClient.getAppId());
            EzAppContextHolder.getContext().setDeviceId(reqClient.getDeviceId());
        }
    }
}