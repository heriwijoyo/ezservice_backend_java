/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.authentication;

import id.ezclouds.biz.arahindonesia.model.login.MemberLoginResult;
import id.ezclouds.biz.arahindonesia.service.core.BizOrganizationService;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberLoginRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.core.auth.request.CoreAppClientAuthRequest;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.shared.model.CoreOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAuthService.java, v 0.1 2024‐01‐28 5:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAuthService {

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private CoreAuthService coreAuthService;

    public CoreAuthResult<String> authAppClient(String orgId, String appId, String clientId, String clientSecret) {
        CoreAuthResult<String> bizAuthResult = new CoreAuthResult<>();

        CoreOrganization coreOrganization = bizOrganizationService.getOrganizationById(orgId);
        if (coreOrganization == null) {
            return bizAuthResult;
        }

        CoreAppClientAuthRequest request = new CoreAppClientAuthRequest();
        request.setAppId(appId);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);

        CoreAuthResult<Void> clientAuthResult = coreAuthService.authAppClient(request);
        if (!clientAuthResult.isSuccess()) {
            return bizAuthResult;
        }

        bizAuthResult.setSuccess(true);
        bizAuthResult.setData(coreOrganization.getCode());
        return bizAuthResult;
    }

    public BizResult loginMember(BizMemberLoginRequest request) {

        return null;
    }
}