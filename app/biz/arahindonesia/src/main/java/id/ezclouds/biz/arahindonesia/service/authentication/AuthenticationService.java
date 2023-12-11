/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.service.authentication;

import id.ezclouds.biz.arahindonesia.constant.AppConstant;
import id.ezclouds.biz.arahindonesia.converter.ModelConverter;
import id.ezclouds.biz.arahindonesia.model.authentication.AppMemberClient;
import id.ezclouds.common.dal.model.AppMemberClientDO;
import id.ezclouds.common.dal.repo.auth.AppMemberClientRepository;
import id.ezclouds.common.util.CollectionUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.error.EzErrorCode;
import id.ezclouds.common.util.error.EzErrorException;
import id.ezclouds.core.shared.context.EzAppContextHolder;
import id.ezclouds.core.shared.model.MemberLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthenticationService.java, v 0.1 2023‐12‐11 2:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AuthenticationService {

    @Autowired
    private AppMemberClientRepository appMemberClientRepository;

    public AppMemberClient authenticate(MemberLogin memberLogin) throws EzErrorException {
        String orgId = EzAppContextHolder.getContext().getOrgId();
        String appId = EzAppContextHolder.getContext().getAppId();

        List<AppMemberClientDO> clientDOs = appMemberClientRepository
                .findAppMemberClient(orgId, appId, memberLogin.getLoginType(), memberLogin.getLoginId());
        AssertUtil.isTrue(CollectionUtil.isNotEmpty(clientDOs), EzErrorCode.MEMBER_LOGIN_FAILED, AppConstant.MEMBER_LOGIN_MESSAGE_FAILED);

        AppMemberClientDO clientDO = clientDOs.get(0);
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        boolean isPasswordMatched = bCrypt.matches(memberLogin.getLoginPassword(), clientDO.getLoginPassword());
        AssertUtil.isTrue(isPasswordMatched, EzErrorCode.MEMBER_LOGIN_FAILED, AppConstant.MEMBER_LOGIN_MESSAGE_FAILED);

        AssertUtil.isTrue(clientDO.isActive(), EzErrorCode.MEMBER_LOGIN_FAILED, AppConstant.MEMBER_LOGIN_MESSAGE_SUSPEND);

        return ModelConverter.convert(clientDO);
    }
}