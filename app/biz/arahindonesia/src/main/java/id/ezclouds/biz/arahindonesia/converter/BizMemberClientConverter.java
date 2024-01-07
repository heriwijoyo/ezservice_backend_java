/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.arahindonesia.converter;

import id.ezclouds.biz.arahindonesia.model.BizStatus;
import id.ezclouds.biz.arahindonesia.model.member.BizMemberClient;
import id.ezclouds.core.auth.model.CoreMemberClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberClientConverter.java, v 0.1 2024‐01‐07 10:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMemberClientConverter {

    public static BizMemberClient convert(CoreMemberClient coreMemberClient) {
        if (coreMemberClient == null) { return null; }
        BizMemberClient bizMemberClient = new BizMemberClient();
        bizMemberClient.setClientId(coreMemberClient.getClientId());
        bizMemberClient.setLoginType(coreMemberClient.getLoginType());
        bizMemberClient.setStatus(BizStatus.getByCode(coreMemberClient.getStatus()));
        return bizMemberClient;
    }
}