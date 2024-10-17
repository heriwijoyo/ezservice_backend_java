/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.auth.AuthScene;
import id.ezclouds.common.model.auth.AuthSession;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.converter.CoreAuthModelConverter;
import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.service.inner.AuthInnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthAdminService.java, v 0.1 2024‐08‐17 7:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthAdminService implements AuthAdminService {

    @Autowired
    private AuthInnerService authInnerService;

    @Override
    public AuthAdminSession authenticateAdminSession(String sessionId) throws EzErrorException {
        EzAuthAdminCommonSessionDO sessionDO = authInnerService.authGetAndTouch(sessionId);
        AuthAdminSession session = new AuthAdminSession();
        session.setOrgId(sessionDO.getOrgId());
        session.setOrgCode(sessionDO.getOrgCode());
        session.setMemberId(sessionDO.getMemberId());
        session.setMemberRoles(sessionDO.getMemberRoles());
        return session;
    }

    @Override
    public void authorizeSessionForRole(AuthAdminSession session, AuthRole role) throws EzErrorException {
        AssertUtil.notNull(session, EzErrorCode.UNAUTHORIZED);
        AssertUtil.notBlank(session.getMemberRoles(), EzErrorCode.UNAUTHORIZED);

        List<String> memberRoles = Arrays.asList(session.getMemberRoles().split(","));
        AssertUtil.isTrue(memberRoles.size() > 0, EzErrorCode.UNAUTHORIZED);

        List<AuthRole> authRoles = memberRoles
                .stream()
                .map(AuthRole::getByCode)
                .collect(Collectors.toList());

        AssertUtil.isTrue(authRoles.contains(role), EzErrorCode.UNAUTHORIZED);
    }

    @Override
    public AuthSession authorizeWebPublicSession(String sessionId) throws EzErrorException {
        AssertUtil.notBlank(sessionId, EzErrorCode.UNAUTHORIZED);
        EzAuthAdminCommonSessionDO sessionDO = authInnerService.authGetAndTouch(sessionId);
        AssertUtil.notNull(sessionDO, EzErrorCode.UNAUTHORIZED);
        AssertUtil.isTrue(sessionDO.getStatus() == 1, EzErrorCode.UNAUTHORIZED);
        AssertUtil.equals(AuthScene.WEB_PUBLIC_SESSION.getCode(), sessionDO.getScene(), EzErrorCode.UNAUTHORIZED);
        AssertUtil.equals(AuthRole.PUBLIC_ACCESS.getCode(), sessionDO.getMemberRoles(), EzErrorCode.UNAUTHORIZED);

        return CoreAuthModelConverter.convert(sessionDO);
    }
}