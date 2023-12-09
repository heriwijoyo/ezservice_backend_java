/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.service.AppClientService;
import id.ezclouds.biz.arahindonesia.service.OrganizationService;
import id.ezclouds.common.dal.model.AppClient;
import id.ezclouds.common.dal.model.Organization;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiBaseRequest;
import id.ezclouds.core.bifrost.app.api.request.RequestAppClient;
import id.ezclouds.core.bifrost.core.BaseRequest;
import id.ezclouds.core.bifrost.core.EzAppContextHolder;
import id.ezclouds.core.bifrost.core.EzAppEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PreBizProcessor.java, v 0.1 2023‐12‐09 3:22 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class PreBizProcessor {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private AppClientService appClientService;

    public void process(EzAppEvent event, BaseRequest request) {

        if (event instanceof ApiEvent) {
            AssertUtil.isTrue((request instanceof ApiBaseRequest), EzErrorCode.PARAM_ILLEGAL, "Unsupported request type");

            ApiBaseRequest apiBaseRequest = (ApiBaseRequest) request;
            RequestAppClient requestAppClient = apiBaseRequest.getAppClient();
            AssertUtil.notNull(requestAppClient, EzErrorCode.PARAM_ILLEGAL, "Request.AppClient is null");

            List<Organization> organizations = organizationService.getOrganizations();
            List<AppClient> appClients = appClientService.getAppClients();

            boolean isOrgIdFound = false;
            Organization requestOrganization = null;
            for (Organization organization : organizations) {
                if (StringUtil.equalsNotNull(requestAppClient.getOrganizationId(), organization.getOrgId())) {
                    isOrgIdFound = true;
                    requestOrganization = organization;
                    break;
                }
            }
            AssertUtil.isTrue(isOrgIdFound, EzErrorCode.UNAUTHORIZED, "Unauthorized client request");

            boolean isClientCredentialPass = false;
            for (AppClient appClient : appClients) {

                if (StringUtil.equalsNotNull(requestAppClient.getApplicationId(), appClient.getAppId())) {
                    boolean orgIdMatch = StringUtil.equalsNotNull(requestOrganization.getOrgId(), appClient.getOrgId());
                    boolean clientIdMatch = StringUtil.equalsNotNull(requestAppClient.getClientId(), appClient.getClientId());
                    boolean clientSecretMatch = StringUtil.equalsNotNull(requestAppClient.getClientSecret(), appClient.getClientSecret());

                    if (orgIdMatch && clientIdMatch && clientSecretMatch) {
                        isClientCredentialPass = true;
                        break;
                    }
                }
            }
            AssertUtil.isTrue(isClientCredentialPass, EzErrorCode.UNAUTHORIZED, "Unauthorized client request");

            EzAppContextHolder.setOrganization(requestOrganization);
        }
    }
}