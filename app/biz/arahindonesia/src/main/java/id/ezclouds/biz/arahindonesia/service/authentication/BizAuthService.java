/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.authentication;

import id.ezclouds.biz.arahindonesia.model.session.BizMemberSession;
import id.ezclouds.biz.arahindonesia.service.core.BizOrganizationService;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberLoginRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.request.CoreAppClientAuthRequest;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.core.auth.result.CoreAuthSessionInfo;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
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

    @Autowired
    private CoreMemberService coreMemberService;

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
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "Invalid request");
                AssertUtil.notBlank(request.getLoginType(), EzErrorCode.ILLEGAL_PARAM, "Invalid request");
                AssertUtil.notBlank(request.getLoginId(), EzErrorCode.ILLEGAL_PARAM, "Invalid request");
                AssertUtil.notBlank(request.getLoginPassword(), EzErrorCode.ILLEGAL_PARAM, "Invalid request");
            }

            @Override
            public void onBizProcess() throws EzErrorException {
                String orgId = EzAppContextHolder.getContext().getOrgId();
                String appId = EzAppContextHolder.getContext().getAppId();
                String deviceId = EzAppContextHolder.getContext().getDeviceId();

                CoreAuthResult<CoreAuthSessionInfo> authResult = coreAuthService.authMemberClient(
                        orgId, appId, request.getLoginType(), request.getLoginId(), request.getLoginPassword(), deviceId
                );

                if (!authResult.isSuccess()) {
                    bizResult.setErrorCode(authResult.getEzErrorCode());
                } else {
                    CoreAuthSessionInfo sessionInfo = authResult.getData();
                    BizMemberSession bizMemberSession = new BizMemberSession();
                    bizMemberSession.setSessionId(sessionInfo.getSessionId());

                    coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());

                    bizResult.setObject(bizMemberSession);
                }
                bizResult.setSuccess(authResult.isSuccess());
            }
        });

        return bizResult;
    }
}