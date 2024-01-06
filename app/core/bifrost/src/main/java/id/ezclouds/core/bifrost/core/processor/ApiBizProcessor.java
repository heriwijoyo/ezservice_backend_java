/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.service.apibiz.*;
import id.ezclouds.biz.arahindonesia.service.request.BizMemberRegisterRequest;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.MemberLoginRequest;
import id.ezclouds.core.bifrost.app.api.request.MemberRegisterRequest;
import id.ezclouds.core.bifrost.core.BaseRequest;
import id.ezclouds.core.bifrost.core.converter.BizRequestConverter;
import id.ezclouds.core.shared.context.EzAppEvent;
import id.ezclouds.core.shared.model.MemberLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBizProcessor.java, v 0.1 2023‐12‐09 3:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ApiBizProcessor implements BizProcessor {

    @Autowired
    private BizAppSettingService bizAppSettingService;

    @Autowired
    private BizCandidateProfileService bizCandidateProfileService;

    @Autowired
    private BizNewsService bizNewsService;

    @Autowired
    private BizMemberProfileService bizMemberProfileService;

    @Autowired
    private BizMemberLoginService bizMemberLoginService;

    @Autowired
    private BizMemberService bizMemberService;

    @Override
    public Object process(EzAppEvent appEvent, BaseRequest request) throws EzErrorException {
        ApiEvent apiEvent = (ApiEvent) appEvent;

        switch (apiEvent) {
            case API_APP_SETTING:
                return bizAppSettingService.getAppSetting();

            case CANDIDATE_PROFILE:
                return bizCandidateProfileService.getCandidateProfile();

            case NEWS:
                return bizNewsService.getActiveNews();

            case MEMBER_PROFILE:
                return bizMemberProfileService.getMemberProfile();

            case MEMBER_LOGIN:
                return bizMemberLoginService.loginMember(composeMemberLogin((MemberLoginRequest)request));

            case MEMBER_REGISTER:
                BizMemberRegisterRequest bizRequest = BizRequestConverter.convert((MemberRegisterRequest) request);
                bizRequest.setSourceId("API");
                return bizMemberService.registerMember(bizRequest);
        }
        return null;
    }

    private MemberLogin composeMemberLogin(MemberLoginRequest loginRequest) {
        MemberLogin memberLogin = new MemberLogin();
        memberLogin.setLoginType(loginRequest.getLoginType());
        memberLogin.setLoginId(loginRequest.getLoginId());
        memberLogin.setLoginPassword(loginRequest.getLoginPassword());
        return memberLogin;
    }
}