/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthBizMemberService;
import id.ezclouds.common.facade.dal.auth.AuthAppClientDAO;
import id.ezclouds.common.facade.dal.auth.AuthMemberClientDAO;
import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.common.model.auth.AuthMemberClient;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.RandomUtil;
import id.ezclouds.common.util.ShardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthBizMemberService.java, v 0.1 2024‐08‐13 6:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthBizMemberService implements AuthBizMemberService {

    @Autowired
    private AuthAppClientDAO authAppClientDAO;

    @Autowired
    private AuthMemberClientDAO authMemberClientDAO;

    @Override
    @Transactional
    public AuthMemberClient getMemberClientOrCreateIfNotExist(String orgId, String memberId, String loginType, String loginId) {
        AuthAppClient appClient = authAppClientDAO.getByOrgId(orgId);
        AuthMemberClient memberClient = authMemberClientDAO.getMemberClient(
                orgId,
                appClient.getAppId(),
                loginType,
                loginId
        );

        if (memberClient != null) {
            return memberClient;
        }

        String currentTime = DateUtil.getCurrentFormattedDate();

        memberClient = new AuthMemberClient();
        memberClient.setClientId(HashUtil.createHash(orgId, memberId, loginId, currentTime));
        memberClient.setOrgId(orgId);
        memberClient.setShard(ShardUtil.getShardId(memberId));
        memberClient.setAppId(appClient.getAppId());
        memberClient.setMemberId(memberId);
        memberClient.setLoginType(loginType);
        memberClient.setLoginId(loginId);
        memberClient.setCreatedTime(currentTime);
        memberClient.setStatus(1);

        authMemberClientDAO.store(memberClient);

        return memberClient;
    }

    @Override
    @Transactional
    public String resetLoginPassword(String clientId) {
        String newPassword = RandomUtil.generateNumberCode(6);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptPassword = bCryptPasswordEncoder.encode(newPassword);

        authMemberClientDAO.updateLoginPassword(clientId, encryptPassword);
        return newPassword;
    }

    @Override
    @Transactional
    public void invalidateMemberSession(String orgId, String client) {

    }
}