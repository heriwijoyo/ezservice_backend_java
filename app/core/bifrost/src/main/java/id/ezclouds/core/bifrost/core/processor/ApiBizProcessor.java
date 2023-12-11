/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.core.processor;

import id.ezclouds.biz.arahindonesia.model.news.SimpleNews;
import id.ezclouds.biz.arahindonesia.service.api.BizAppSettingService;
import id.ezclouds.biz.arahindonesia.service.NewsService;
import id.ezclouds.biz.arahindonesia.service.api.BizCandidateProfileService;
import id.ezclouds.biz.arahindonesia.service.api.BizMemberLoginService;
import id.ezclouds.biz.arahindonesia.service.api.BizMemberProfileService;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.MemberLoginRequest;
import id.ezclouds.core.shared.result.ListResult;
import id.ezclouds.core.bifrost.core.BaseRequest;
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
    private NewsService newsService;

    @Autowired
    private BizMemberProfileService bizMemberProfileService;

    @Autowired
    private BizMemberLoginService bizMemberLoginService;

    @Override
    public Object process(EzAppEvent appEvent, BaseRequest request) throws EzErrorException {
        ApiEvent apiEvent = (ApiEvent) appEvent;

        switch (apiEvent) {
            case API_APP_SETTING:
                return bizAppSettingService.getAppSetting();

            case CANDIDATE_PROFILE:
                return bizCandidateProfileService.getCandidateProfile();

            case NEWS:
                ListResult<SimpleNews> listResult = new ListResult<>();
                listResult.setItems(newsService.getActiveListNews());
                return listResult;

            case MEMBER_PROFILE:
                return bizMemberProfileService.getMemberProfile();

            case MEMBER_LOGIN:
                return bizMemberLoginService.loginMember(composeMemberLogin((MemberLoginRequest)request));
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