/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.auth.converter.CoreMemberClientConverter;
import id.ezclouds.core.auth.dataobject.CoreMemberClientDO;
import id.ezclouds.core.auth.model.CoreMemberClient;
import id.ezclouds.core.auth.repo.CoreMemberClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthService.java, v 0.1 2024‐01‐07 9:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthService {

    @Autowired
    private CoreMemberClientRepository coreMemberClientRepository;

    public void createMemberClient(CoreMemberClient memberClient) {
        CoreMemberClientDO memberClientDO = CoreMemberClientConverter.convert(memberClient);
        memberClientDO.setClientId(HashUtil.createHash(memberClient.getLoginType(), memberClient.getMemberId()));
        memberClientDO.setCreatedTime(DateUtil.getCurrentFormattedDate());

        coreMemberClientRepository.save(memberClientDO);
    }

    public CoreMemberClient getOptimisticMemberClient(String loginType, String memberId) {
        String clientId = HashUtil.createHash(loginType, memberId);
        CoreMemberClientDO memberClientDO = coreMemberClientRepository.findById(clientId).orElse(null);
        AssertUtil.notNull(memberClientDO, EzErrorCode.MEMBER_CLIENT_NOT_FOUND, "Member client not found");
        return CoreMemberClientConverter.convert(memberClientDO);
    }
}