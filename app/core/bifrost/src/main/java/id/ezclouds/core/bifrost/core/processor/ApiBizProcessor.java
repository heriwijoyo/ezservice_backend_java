/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.service.apibiz.*;
import id.ezclouds.biz.arahindonesia.service.apibiz.BizAuthService;
import id.ezclouds.biz.arahindonesia.service.request.*;
import id.ezclouds.biz.arahindonesia.service.result.BizResult;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiRequest;
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

        if (appEvent instanceof ApiEvent) {
            ApiEvent apiEvent = (ApiEvent) appEvent;
            ApiRequest apiRequest = (ApiRequest) request;

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
                    BizRequestConverter<BizMemberLoginRequest> loginConverter = new BizRequestConverter<>(BizRequestConverter.MEMBER_LOGIN);
                    return bizAuthService.memberLogin(loginConverter.convert(apiRequest));

                case API_SESSION_CHECK:
                    return bizAuthService.memberSessionCheck();

                case API_MEMBER_LOGOUT:
                    return bizAuthService.memberLogout();

                case API_MEMBER_UPDATE_PASSWORD:
                    BizRequestConverter<BizMemberUpdatePasswordRequest> converter = new BizRequestConverter<>(BizRequestConverter.UPDATE_PASSWORD);
                    return bizAuthService.memberUpdatePassword(converter.convert(apiRequest));

                case API_MEMBER_RESET_PASSWORD:
                    BizRequestConverter<BizMemberResetPasswordRequest> resetConverter = new BizRequestConverter<>(BizRequestConverter.RESET_PASSWORD);
                    return bizAuthService.memberResetPassword(resetConverter.convert(apiRequest));

                case API_MEMBER_VERIFY_COMMON_SESSION:
                    BizRequestConverter<BizVerifyCommonSessionRequest> verifyConverter = new BizRequestConverter<>(BizRequestConverter.VERIFY_COMMON_SESSION);
                    return bizAuthService.memberVerifyCommonSession(verifyConverter.convert(apiRequest));

                case API_MEMBER_REGISTER:
                    BizRequestConverter<BizMemberRegisterRequest> registerConverter = new BizRequestConverter<>(BizRequestConverter.MEMBER_REGISTER);
                    BizMemberRegisterRequest bizRequest = registerConverter.convert(apiRequest);
                    bizRequest.getExtendInfo().put(AppConstant.ExtKey.SOURCE_ID, SOURCE_ID);
                    return bizMemberService.registerMember(bizRequest);

                case API_MEMBER_UPDATE_AVATAR:
                    BizRequestConverter<BizMemberUpdateAvatarRequest> avatarConverter = new BizRequestConverter<>(BizRequestConverter.UPDATE_AVATAR);
                    return bizMemberService.memberUpdateAvatar(avatarConverter.convert(apiRequest));
            }
        }

        BizResult bizResult = new BizResult();
        bizResult.setErrorCode(EzErrorCode.SYSTEM_ERROR);
        bizResult.setErrorLocation(getClass().getName());
        bizResult.setErrorMessage("Undefined bizProcessor");
        return bizResult;
    }
}