/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthBizMemberService;
import id.ezclouds.common.facade.biz.util.BizContextUtil;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.facade.dal.auth.AuthAppClientDAO;
import id.ezclouds.common.facade.dal.auth.AuthMemberClientDAO;
import id.ezclouds.common.facade.dal.auth.AuthMemberClientSessionDAO;
import id.ezclouds.common.model.auth.AuthAppClient;
import id.ezclouds.common.model.auth.AuthMemberClient;
import id.ezclouds.common.model.auth.AuthMemberClientSession;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.RandomUtil;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.util.Date;

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

    @Autowired
    private AuthMemberClientSessionDAO authMemberClientSessionDAO;

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private TransactionTemplate transactionTemplate;

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
    public void authMemberAppSession(String sessionId, AuthRole authRole) {

        String orgId = BizContextUtil.getOrgId();
        String appId = BizContextUtil.getAppId();
        String clientId = BizContextUtil.getClientId();

        int expiryExtendDays = coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.MEMBER_CLIENT_SESSION_EXPIRY_DAYS)
                .getIntValue();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                AuthMemberClientSession session = authMemberClientSessionDAO
                        .findSessionById(sessionId);
                AssertUtil.notNull(session, EzErrorCode.UNAUTHORIZED);
                AssertUtil.equals(orgId, session.getOrgId(), EzErrorCode.UNAUTHORIZED);
                AssertUtil.equals(appId, session.getAppId(), EzErrorCode.UNAUTHORIZED);
                AssertUtil.equals(clientId, session.getClientId(), EzErrorCode.UNAUTHORIZED);

                Date updateExpiryDate = DateUtil.getDateAfterDays(new Date(), expiryExtendDays);
                session.setExpiryTime(DateUtil.getFormattedDate(updateExpiryDate));
                authMemberClientSessionDAO.store(session);
            }
        });

    }

    @Override
    @Transactional
    public void invalidateMemberSession(String orgId, String client) {

    }
}