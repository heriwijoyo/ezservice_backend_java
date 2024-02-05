/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.constant.BizConstant;
import id.ezclouds.biz.arahindonesia.converter.BizMemberConverter;
import id.ezclouds.biz.arahindonesia.converter.BizMessageTemplateConverter;
import id.ezclouds.biz.arahindonesia.model.authentication.BizMemberCommonSession;
import id.ezclouds.biz.arahindonesia.service.dataservice.AppConfigService;
import id.ezclouds.biz.arahindonesia.service.dataservice.AppMemberFlagService;
import id.ezclouds.biz.arahindonesia.service.dataservice.AppSubOrganizationService;
import id.ezclouds.biz.arahindonesia.service.request.BizCommonSessionVerifyRequest;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberResetPasswordRequest;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberUpdatePasswordRequest;
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
import id.ezclouds.core.auth.request.CoreMemberCommonSessionRequest;
import id.ezclouds.core.auth.result.CoreCommonSession;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.auth.service.CoreAuthService;
import id.ezclouds.core.integration.request.WhatsappSendRequest;
import id.ezclouds.core.integration.service.CoreIntegrationService;
import id.ezclouds.core.member.model.CoreMember;
import id.ezclouds.core.member.model.CoreMemberExtension;
import id.ezclouds.core.member.service.CoreMemberService;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.model.CoreOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAuthService.java, v 0.1 2024‐01‐28 5:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAuthService extends BizBaseService {

    @Autowired
    private BizOrganizationService bizOrganizationService;

    @Autowired
    private CoreAuthService coreAuthService;

    @Autowired
    private CoreMemberService coreMemberService;

    @Autowired
    private AppSubOrganizationService appSubOrganizationService;

    @Autowired
    private AppMemberFlagService appMemberFlagService;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private CoreIntegrationService coreIntegrationService;

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

    public BizResult memberLogin(BizMemberLoginRequest request) {
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
            public void onBizProcess() throws Exception {
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

                CoreAuthMemberSessionInfo sessionInfo = coreAuthService.authMemberClient(authRequest);

                BizMemberLoginResult loginResult = new BizMemberLoginResult();
                loginResult.setMemberSessionId(sessionInfo.getSessionId());
                loginResult.setSuccessMessage(AppConstant.MEMBER_LOGIN_MESSAGE_SUCCESS);
                loginResult.setMemberFlags(appMemberFlagService.getAppMemberFlag(orgId, sessionInfo.getMemberId()));

                //support old version
                //TODO: remove when all client updated into newer version
                loginResult.setMemberSessionCode(sessionInfo.getSessionId());

                CoreMember coreMember = coreMemberService.getOptimisticCoreMember(sessionInfo.getMemberId());
                CoreMemberExtension coreMemberExtension = coreMemberService.getOptimisticCoreMemberExtension(sessionInfo.getMemberId());

                if (appVersionNo >= AppConstant.APP_V2_START_VERSION_NO) {
                    BizMember bizMember = BizMemberConverter.convert(coreMember, coreMemberExtension);
                    bizMember.setSubOrganization(appSubOrganizationService.getSubOrganizationById(coreMember.getSubOrgId()));
                    loginResult.setBizMember(bizMember);
                }
                else {
                    MemberBase memberBase = BizMemberConverter.convert(coreMember);
                    loginResult.setMemberBase(memberBase);
                }

                bizResult.setObject(loginResult);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult memberResetPassword(BizMemberResetPasswordRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM, "Invalid request");
                AssertUtil.notBlank(request.getLoginType(), EzErrorCode.ILLEGAL_PARAM, "Invalid request");
                AssertUtil.notBlank(request.getLoginId(), EzErrorCode.ILLEGAL_PARAM, "Invalid request");
            }

            @Override
            public void onBizProcess() throws Exception {
                String orgId = EzAppContextHolder.getContext().getOrgId();
                String appId = EzAppContextHolder.getContext().getAppId();
                String deviceId = EzAppContextHolder.getContext().getDeviceId();

                CoreMemberCommonSessionRequest authRequest = new CoreMemberCommonSessionRequest();
                authRequest.setOrgId(orgId);
                authRequest.setAppId(appId);
                authRequest.setLoginType(request.getLoginType());
                authRequest.setLoginId(request.getLoginId());
                authRequest.setDeviceId(deviceId);
                authRequest.setScene(BizConstant.Auth.COMMON_SESSION_SCENE_RESET_MEMBER_PASSWORD);
                authRequest.setVerifyStrategy(BizConstant.Auth.COMMON_SESSION_VERIFY_STRATEGY_WHATSAPP);

                CoreCommonSession sessionInfo = coreAuthService.createMemberCommonSession(authRequest);
                BizMemberCommonSession commonSession = new BizMemberCommonSession();
                commonSession.setSessionId(sessionInfo.getSessionId());
                commonSession.setScene(sessionInfo.getScene());
                commonSession.setVerifyStrategy(sessionInfo.getVerifyStrategy());
                commonSession.setVerifyTarget(sessionInfo.getVerifyTarget());

                memberCommonSessionSendWhatsapp(sessionInfo);

                bizResult.setObject(commonSession);
                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult memberVerifyCommonSession(BizCommonSessionVerifyRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getSessionId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getScene(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getVerifyStrategy(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreCommonSession commonSession = new CoreCommonSession();
                commonSession.setSessionId(request.getSessionId());
                commonSession.setScene(request.getScene());
                commonSession.setVerifyStrategy(request.getVerifyStrategy());
                commonSession.setVerifyCode(request.getVerifyCode());
                coreAuthService.verifyCommonSession(commonSession);
                coreAuthService.invalidateCommonSession(commonSession);

                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
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

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
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
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = coreAuthService.authMemberSession(sessionId);

                bizResult.setSuccess(true);
                bizResult.setObject(sessionInfo.getMemberId());
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    public BizResult memberUpdatePassword(BizMemberUpdatePasswordRequest request) {
        final BizResult bizResult = new BizResult();

        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getMode(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getNewPassword(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                CoreAuthMemberSessionInfo sessionInfo = null;
                if (BizConstant.UPDATE_PASSWORD_MODE_MEMBER_SESSION.equals(request.getMode())) {
                    String memberSessionId = EzAppContextHolder.getContext().getMemberSessionId();
                    sessionInfo = coreAuthService.authMemberSession(memberSessionId);
                }
                if (BizConstant.UPDATE_PASSWORD_MODE_RESET_SESSION.equals(request.getMode())) {
                    //TODO: add this capability later
                }

                coreAuthService.updateMemberClientPassword(sessionInfo.getClientId(), request.getNewPassword());

                String extForceUpdate = request.getExtendInfo().get("FORCED_UPDATE_PASSWORD");
                if (Boolean.parseBoolean(extForceUpdate)) {
                    String orgId = EzAppContextHolder.getContext().getOrgId();
                    appMemberFlagService.invalidateAppMemberFlag(
                            orgId,
                            sessionInfo.getMemberId(),
                            BizConstant.MemberFlag.NEED_UPDATE_PASSWORD
                    );
                }

                bizResult.setSuccess(true);
                bizResult.setObject(BizConstant.Message.UPDATE_PASSWORD_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });

        return bizResult;
    }

    private void memberCommonSessionSendWhatsapp(CoreCommonSession commonSession) {
        String messageTemplate = appConfigService.getMessageTemplate(BizConstant.TemplateKey.WA_RESET_PASS_VERIFY_CODE);
        Map<String, String> values = new HashMap<>();
        values.put("VERIFY_CODE", commonSession.getVerifyCode());
        values.put("EXPIRY_LABEL", commonSession.getExpiryTime());

        String whatsappMessage = BizMessageTemplateConverter.getMessage(messageTemplate, values);
        if (whatsappMessage != null) {
            System.out.println(whatsappMessage);
            WhatsappSendRequest request = new WhatsappSendRequest();
            request.setPhoneNumber(commonSession.getVerifyTarget());
            request.setMessage(whatsappMessage);

            coreIntegrationService.sendWhatsappMessage(request);
        } else {
            System.out.println("==============> ASUUUUUUUUUUUUU");
        }
    }
}