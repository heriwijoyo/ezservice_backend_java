/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.converter;

import id.ezclouds.biz.election.model.BizStatus;
import id.ezclouds.biz.election.model.member.BizMemberClient;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberClientConverter.java, v 0.1 2024‐01‐07 10:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberClientConverter {

    public static BizMemberClient convert(CoreAuthMemberClient coreAuthMemberClient) {
        if (coreAuthMemberClient == null) { return null; }
        BizMemberClient bizMemberClient = new BizMemberClient();
        bizMemberClient.setClientId(coreAuthMemberClient.getClientId());
        bizMemberClient.setLoginType(coreAuthMemberClient.getLoginType());
        bizMemberClient.setStatus(BizStatus.getByCode(coreAuthMemberClient.getStatus()));
        return bizMemberClient;
    }
}