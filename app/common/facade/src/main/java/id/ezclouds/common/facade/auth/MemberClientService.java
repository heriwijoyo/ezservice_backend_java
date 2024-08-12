/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.auth;

import id.ezclouds.common.model.auth.MemberClient;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: MemberClientService.java, v 0.1 2024‐08‐12 9:12 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface MemberClientService {
    MemberClient getMemberClient(String orgId, String appId, String loginType, String loginId);
}