/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthAdminService;
import id.ezclouds.common.model.auth.AuthAdminSession;
import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.service.inner.AuthInnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthAdminService.java, v 0.1 2024‐08‐17 7:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthAdminService implements AuthAdminService {

    @Autowired
    private AuthInnerService authInnerService;

    @Override
    public AuthAdminSession authorizeAdminSession(String sessionId) {
        EzAuthAdminCommonSessionDO sessionDO = authInnerService.authGetAndTouch(sessionId);
        AuthAdminSession session = new AuthAdminSession();
        session.setOrgId(sessionDO.getOrgId());
        session.setOrgCode(sessionDO.getOrgCode());
        session.setMemberId(sessionDO.getMemberId());
        session.setMemberRoles(sessionDO.getMemberRoles());
        return session;
    }
}