/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.converter;

import id.ezclouds.common.model.auth.AuthMemberClient;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.auth.dataobject.EzAuthMemberClientDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientStoreConverter.java, v 0.1 2024‐08‐14 6:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAuthMemberClientStoreConverter extends TemplateModelConverter<AuthMemberClient, EzAuthMemberClientDO> {

    @Override
    protected EzAuthMemberClientDO safeConvert(AuthMemberClient input) {
        EzAuthMemberClientDO memberClientDO = new EzAuthMemberClientDO();
        memberClientDO.setClientId(input.getClientId());
        memberClientDO.setOrgId(input.getOrgId());
        memberClientDO.setShard(input.getShard());
        memberClientDO.setAppId(input.getAppId());
        memberClientDO.setMemberId(input.getMemberId());
        memberClientDO.setLoginType(input.getLoginType());
        memberClientDO.setLoginId(input.getLoginId());
        memberClientDO.setLoginPassword(input.getLoginPassword());
        memberClientDO.setCreatedTime(input.getCreatedTime());
        memberClientDO.setStatus(input.getStatus());
        return memberClientDO;
    }
}