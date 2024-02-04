/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.service.apibiz.*;
import id.ezclouds.biz.arahindonesia.service.apibiz.BizAuthService;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberLoginRequest;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberUpdatePasswordRequest;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.MemberLoginRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberRegisterRequest;
import id.ezclouds.core.bifrost.core.BaseRequest;
import id.ezclouds.core.bifrost.core.converter.BizRequestConverter;
import id.ezclouds.core.shared.context.EzAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBizProcessor.java, v 0.1 2023‐12‐09 3:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ApiBizProcessor implements BizProcessor {

    private static final String SOURCE_ID = "API";

    @Autowired
    private BizAppSettingService bizAppSettingService;

    @Autowired
    private BizCandidateProfileService bizCandidateProfileService;

    @Autowired
    private BizNewsService bizNewsService;

    @Autowired
    private BizMemberProfileService bizMemberProfileService;

    @Autowired
    private BizMemberService bizMemberService;

    @Autowired
    private BizAuthService bizAuthService;

    @Override
    public BizResult process(EzAppEvent appEvent, BaseRequest request) throws EzErrorException {
        ApiEvent apiEvent = (ApiEvent) appEvent;

        switch (apiEvent) {
            case API_APP_SETTING:
                return bizAppSettingService.getAppSetting();

            case API_CANDIDATE_PROFILE:
                return bizCandidateProfileService.getCandidateProfile();

            case API_NEWS:
                return bizNewsService.getActiveNews();

            case API_MEMBER_PROFILE:
                return bizMemberService.getMemberProfile();

            case API_MEMBER_LOGIN:
                return bizAuthService.memberLogin(composeMemberLogin((MemberLoginRequest)request));

            case API_SESSION_CHECK:
                return bizAuthService.memberSessionCheck();

            case API_MEMBER_LOGOUT:
                return bizAuthService.memberLogout();

            case API_MEMBER_UPDATE_PASSWORD:
                BizRequestConverter<BizMemberUpdatePasswordRequest> converter = new BizRequestConverter<>(BizRequestConverter.UPDATE_PASSWORD);
                return bizAuthService.memberUpdatePassword(converter.convert(request));

            case API_MEMBER_REGISTER:
                BizMemberRegisterRequest bizRequest = BizRequestConverter.convert((MemberRegisterRequest) request);
                bizRequest.getExtendInfo().put(AppConstant.ExtKey.SOURCE_ID, SOURCE_ID);
                return bizMemberService.registerMember(bizRequest);
        }

        BizResult bizResult = new BizResult();
        bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
        bizResult.setErrorLocation(getClass().getName());
        bizResult.setErrorMessage("Undefined bizProcessor");
        return bizResult;
    }

    private BizMemberLoginRequest composeMemberLogin(MemberLoginRequest loginRequest) {
        if (loginRequest == null) { return null; }
        BizMemberLoginRequest memberLogin = new BizMemberLoginRequest();
        memberLogin.setLoginType(loginRequest.getLoginType());
        memberLogin.setLoginId(loginRequest.getLoginId());
        memberLogin.setLoginPassword(loginRequest.getLoginPassword());
        return memberLogin;
    }
}