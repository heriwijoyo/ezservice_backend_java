/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.converter;

import id.ezclouds.common.model.auth.AuthMemberClientSession;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.auth.dataobject.AuthMemberClientSessionDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthMemberClientSessionConverter.java, v 0.1 2024‐09‐30 1:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class AuthMemberClientSessionConverter extends CommonDOModelConverter<AuthMemberClientSessionDO, AuthMemberClientSession> {

    @Override
    protected AuthMemberClientSession safeConvertQuery(AuthMemberClientSessionDO dataObject) {
        AuthMemberClientSession session = new AuthMemberClientSession();
        session.setSessionId(dataObject.getSessionId());
        session.setOrgId(dataObject.getOrgId());
        session.setShard(dataObject.getShard());
        session.setAppId(dataObject.getAppId());
        session.setClientId(dataObject.getClientId());
        session.setMemberId(dataObject.getMemberId());
        session.setMemberRoles(dataObject.getMemberRoles());
        session.setDeviceId(dataObject.getDeviceId());
        session.setCreatedTime(dataObject.getCreatedTime());
        session.setExpiryTime(dataObject.getExpiryTime());
        session.setStatus(dataObject.getStatus());
        return session;
    }

    @Override
    protected AuthMemberClientSessionDO safeConvertStore(AuthMemberClientSession model) {
        AuthMemberClientSessionDO sessionDO = new AuthMemberClientSessionDO();
        sessionDO.setSessionId(model.getSessionId());
        sessionDO.setOrgId(model.getOrgId());
        sessionDO.setShard(model.getShard());
        sessionDO.setAppId(model.getAppId());
        sessionDO.setClientId(model.getClientId());
        sessionDO.setMemberId(model.getMemberId());
        sessionDO.setMemberRoles(model.getMemberRoles());
        sessionDO.setDeviceId(model.getDeviceId());
        sessionDO.setCreatedTime(model.getCreatedTime());
        sessionDO.setExpiryTime(model.getExpiryTime());
        sessionDO.setStatus(model.getStatus());
        return sessionDO;
    }
}