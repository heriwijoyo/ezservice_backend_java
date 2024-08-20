/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.converter;

import id.ezclouds.common.model.auth.AuthMemberClient;
import id.ezclouds.common.model.util.TemplateModelConverter;
import id.ezclouds.core.dal.auth.dataobject.DalAuthMemberClientDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientQueryConverter.java, v 0.1 2024‐08‐13 11:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class EzAuthMemberClientQueryConverter extends TemplateModelConverter<DalAuthMemberClientDO, AuthMemberClient> {

    @Override
    protected AuthMemberClient safeConvert(DalAuthMemberClientDO input) {
        AuthMemberClient memberClient = new AuthMemberClient();
        memberClient.setClientId(input.getClientId());
        memberClient.setOrgId(input.getOrgId());
        memberClient.setShard(input.getShard());
        memberClient.setAppId(input.getAppId());
        memberClient.setMemberId(input.getMemberId());
        memberClient.setLoginType(input.getLoginType());
        memberClient.setLoginId(input.getLoginId());
        memberClient.setLoginType(input.getLoginType());
        memberClient.setLoginPassword(input.getLoginPassword());
        memberClient.setCreatedTime(input.getCreatedTime());
        return memberClient;
    }
}