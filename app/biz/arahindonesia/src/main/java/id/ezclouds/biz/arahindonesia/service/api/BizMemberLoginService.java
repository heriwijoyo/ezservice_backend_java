/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.api;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.model.login.MemberLoginResult;
import id.ezclouds.biz.arahindonesia.model.member.MemberBase;
import id.ezclouds.biz.arahindonesia.service.MemberService;
import id.ezclouds.biz.arahindonesia.service.authentication.AuthenticationService;
import id.ezclouds.common.dal.model.AppMemberClientDO;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.core.shared.model.MemberLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MemberLoginResult loginMember(MemberLogin memberLogin) {

        AppMemberClientDO appMemberClientDO = authenticationService.authenticate(memberLogin);
        MemberBase memberBase = memberService.getMemberById(appMemberClientDO.getMemberId());

        AssertUtil.notNull(memberBase, EzErrorCode.MEMBER_NOT_FOUND);
        AssertUtil.isTrue(memberBase.getStatus() == AppConstant.COMMON_STATUS_ACTIVE, EzErrorCode.MEMBER_LOGIN_FAILED, AppConstant.MEMBER_LOGIN_MESSAGE_SUSPEND);

        // create member session

        MemberLoginResult loginResult = new MemberLoginResult();
        loginResult.setMemberBase(memberBase);

        return loginResult;
    }
}