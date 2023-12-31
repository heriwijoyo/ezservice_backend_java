/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.apibiz;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.model.authentication.AppMemberClient;
import id.ezclouds.biz.arahindonesia.model.login.MemberLoginResult;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.biz.arahindonesia.model.session.MemberSession;
import id.ezclouds.biz.arahindonesia.service.core.MemberService;
import id.ezclouds.biz.arahindonesia.service.authentication.AuthenticationService;
import id.ezclouds.biz.arahindonesia.service.session.MemberSessionService;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.core.shared.model.MemberLogin;
import id.ezclouds.core.shared.service.CoreSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberLoginService.java, v 0.1 2023‐12‐11 3:15 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizMemberLoginService {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberSessionService memberSessionService;

    @Autowired
    private CoreSequenceService coreSequenceService;

    public MemberLoginResult loginMember(MemberLogin memberLogin) {

        AppMemberClient appMemberClient = authenticationService.authenticate(memberLogin);
        MemberBase memberBase = memberService.getMemberById(appMemberClient.getMemberId(), appMemberClient.getOrgId());

        AssertUtil.notNull(memberBase, EzErrorCode.MEMBER_NOT_FOUND, AppConstant.MEMBER_LOGIN_MESSAGE_FAILED);
        AssertUtil.isTrue(memberBase.isActive(), EzErrorCode.MEMBER_LOGIN_FAILED, AppConstant.MEMBER_LOGIN_MESSAGE_SUSPEND);

        MemberSession memberSession = memberSessionService.createMemberSession(appMemberClient);

        MemberLoginResult loginResult = new MemberLoginResult();
        loginResult.setMemberSessionId(memberSession.getSessionId());
        loginResult.setMemberRoleCode(memberBase.getRole());
        loginResult.setSuccessMessage(AppConstant.MEMBER_LOGIN_MESSAGE_SUCCESS);
        loginResult.setMemberBase(memberBase);

        return loginResult;
    }

    @Transactional
    public String generateSequence() {
        return coreSequenceService.generateSequence("RJL0", "201", "MEMBER_ID");
    }
}