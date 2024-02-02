/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.converter.BizMemberConverter;
import id.ezclouds.biz.arahindonesia.service.result.BizMemberLoginResult;
import id.ezclouds.biz.arahindonesia.model.member.BizMember;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.biz.arahindonesia.service.core.BizOrganizationService;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberLoginRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.biz.arahindonesia.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.request.CoreAppClientAuthRequest;
import id.ezclouds.core.auth.request.CoreMemberClientAuthRequest;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.core.auth.result.CoreAuthSessionInfo;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
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
                int appVersionNo = EzAppContextHolder.getContext().getAppVersionNo();

                CoreMemberClientAuthRequest authRequest = new CoreMemberClientAuthRequest();
                authRequest.setOrgId(orgId);
                authRequest.setAppId(appId);
                authRequest.setLoginType(request.getLoginType());
                authRequest.setLoginId(request.getLoginId());
                authRequest.setLoginPass(request.getLoginPassword());
                authRequest.setDeviceId(deviceId);
                CoreAuthResult<CoreAuthSessionInfo> authResult = coreAuthService.authMemberClient(authRequest);

                if (!authResult.isSuccess()) {
                    bizResult.setErrorCode(authResult.getEzErrorCode());
                } else {
                    CoreAuthSessionInfo sessionInfo = authResult.getData();

                    BizMemberLoginResult loginResult = new BizMemberLoginResult();
                    loginResult.setMemberSessionId(sessionInfo.getSessionId());
                    loginResult.setSuccessMessage(AppConstant.MEMBER_LOGIN_MESSAGE_SUCCESS);

                    CoreMember coreMember = coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());
                    CoreMemberExtension coreMemberExtension = coreMemberService.getOptimisticCoreMemberExtension(sessionInfo.getMemberId());

                    if (appVersionNo >= AppConstant.APP_V2_START_VERSION_NO) {
                        BizMember bizMember = BizMemberConverter.convert(coreMember, coreMemberExtension);
                        loginResult.setBizMember(bizMember);
                    }
                    else {
                        MemberBase memberBase = BizMemberConverter.convert(coreMember);
                        loginResult.setMemberBase(memberBase);
                    }

                    bizResult.setObject(loginResult);
                }
                bizResult.setSuccess(authResult.isSuccess());
            }
        });

        return bizResult;
    }

    public BizResult memberLogout() {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws EzErrorException {
                String sessionId = EzAppContextHolder.getContext().getMemberSessionId();
                CoreAuthResult<Void> authResult = coreAuthService.invalidateMemberSession(sessionId);

                bizResult.setSuccess(authResult.isSuccess());
            }
        });

        return bizResult;
    }

    public BizResult memberSessionCheck() {
        final BizResult bizResult = new BizResult();

        String sessionId = EzAppContextHolder.getContext().getMemberSessionId();

        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(sessionId, EzErrorCode.SESSION_INVALID);
            }

            @Override
            public void onBizProcess() throws EzErrorException {
                CoreAuthResult<String> authResult = coreAuthService.authMemberSession(sessionId);

                bizResult.setSuccess(authResult.isSuccess());
                if (authResult.isSuccess()) {
                    bizResult.setObject(authResult.getData());
                } else {
                    bizResult.setErrorCode(authResult.getEzErrorCode());
                }
            }
        });

        return bizResult;
    }
}