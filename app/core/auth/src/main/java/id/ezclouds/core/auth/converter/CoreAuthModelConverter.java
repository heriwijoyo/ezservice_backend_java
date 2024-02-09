/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.converter;

import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.dataobject.EzAuthAppClientDO;
import id.ezclouds.core.auth.dataobject.EzAuthMemberClientDO;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.model.CoreAuthAppClient;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthModelConverter.java, v 0.1 2024‐01‐07 9:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$ */
public class CoreAuthModelConverter {

    public static CoreAuthAppClient convert(EzAuthAppClientDO appClientDO) {
        if (appClientDO == null) { return null; }
        CoreAuthAppClient appClient = new CoreAuthAppClient();
        appClient.setId(appClientDO.getId());
        appClient.setOrgId(appClientDO.getOrgId());
        appClient.setAppId(appClientDO.getAppId());
        appClient.setClientId(appClientDO.getClientId());
        appClient.setClientSecret(appClientDO.getClientSecret());
        return appClient;
    }

    public static CoreAuthMemberClient convert(EzAuthMemberClientDO clientDO) {
        if (clientDO == null) { return null; }
        CoreAuthMemberClient client = new CoreAuthMemberClient();
        client.setClientId(clientDO.getClientId());
        client.setOrgId(clientDO.getOrgId());
        client.setShard(clientDO.getShard());
        client.setAppId(clientDO.getAppId());
        client.setMemberId(clientDO.getMemberId());
        client.setLoginType(clientDO.getLoginType());
        client.setLoginId(clientDO.getLoginId());
        client.setLoginPassword(clientDO.getLoginPassword());
        client.setLoginPin(clientDO.getLoginPin());
        client.setStatus(clientDO.getStatus());
        return client;
    }

    public static EzAuthMemberClientDO convert(CoreAuthMemberClient client) {
        if (client == null) { return null; }
        EzAuthMemberClientDO clientDO = new EzAuthMemberClientDO();
        clientDO.setClientId(client.getClientId());
        clientDO.setOrgId(client.getOrgId());
        clientDO.setShard(client.getShard());
        clientDO.setAppId(client.getAppId());
        clientDO.setMemberId(client.getMemberId());
        clientDO.setLoginType(client.getLoginType());
        clientDO.setLoginId(client.getLoginId());
        clientDO.setLoginPassword(client.getLoginPassword());
        clientDO.setLoginPin(client.getLoginPin());
        clientDO.setStatus(client.getStatus());
        return clientDO;
    }

    public static CoreAuthAdminSession convert(EzAuthAdminCommonSessionDO sessionDO) {
        if (sessionDO == null) { return null; }
        CoreAuthAdminSession session = new CoreAuthAdminSession();
        session.setSessionId(sessionDO.getSessionId());
        session.setSessionCode(sessionDO.getSessionCode());
        session.setScene(sessionDO.getScene());
        session.setOrgId(sessionDO.getOrgId());
        session.setOrgCode(sessionDO.getOrgCode());
        session.setAppId(sessionDO.getAppId());
        session.setClientId(sessionDO.getClientId());
        session.setMemberId(sessionDO.getMemberId());
        session.setMemberRoles(sessionDO.getMemberRoles());
        session.setCreatedTime(sessionDO.getCreatedTime());
        session.setExpiryTime(sessionDO.getExpiryTime());
        session.setStatus(sessionDO.getStatus());
        return session;
    }
}