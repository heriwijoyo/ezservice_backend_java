/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.converter;

import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.auth.AuthScene;
import id.ezclouds.common.model.auth.WebPublicSession;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.auth.dataobject.WebPublicSessionDO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: WebPublicSessionConverter.java, v 0.1 2024‐10‐10 12:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class WebPublicSessionConverter extends CommonDOModelConverter<WebPublicSessionDO, WebPublicSession> {

    @Override
    protected WebPublicSession safeConvertQuery(WebPublicSessionDO dataObject) {
        WebPublicSession session = new WebPublicSession();
        session.setSessionId(dataObject.getSessionId());
        session.setAuthScene(AuthScene.getByCode(dataObject.getScene()));
        session.setOrgId(dataObject.getOrgId());
        session.setAuthRoles(getAuthRoles(dataObject.getMemberRoles()));
        session.setExpiryTime(dataObject.getExpiryTime());
        return session;
    }

    @Override
    protected WebPublicSessionDO safeConvertStore(WebPublicSession model) {
        WebPublicSessionDO sessionDO = new WebPublicSessionDO();
        sessionDO.setSessionId(model.getSessionId());
        sessionDO.setSessionCode(model.getSessionCode());
        sessionDO.setScene(model.getAuthScene().getCode());
        sessionDO.setOrgId(model.getOrgId());
        sessionDO.setOrgCode("000");
        sessionDO.setAppId("APP_ID");
        sessionDO.setClientId("CLIENT_ID");
        sessionDO.setMemberId("MEMBER_ID");
        sessionDO.setMemberRoles(getRoles(model.getAuthRoles()));
        sessionDO.setCreatedTime(model.getCreatedTime());
        sessionDO.setExpiryTime(model.getExpiryTime());
        sessionDO.setStatus(1);
        return sessionDO;
    }

    private String getRoles(List<AuthRole> authRoles) {
        return authRoles.stream().map(AuthRole::getCode).collect(Collectors.joining());
    }

    private List<AuthRole> getAuthRoles(String roles) {
        return Arrays.stream(roles.split(",")).map(AuthRole::getByCode).collect(Collectors.toList());
    }
}