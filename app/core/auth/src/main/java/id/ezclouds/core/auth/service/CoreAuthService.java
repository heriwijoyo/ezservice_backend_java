/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.ShardUtil;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.auth.constant.CoreAuthConfig;
import id.ezclouds.core.auth.constant.CoreAuthConstant;
import id.ezclouds.core.auth.converter.CoreAuthModelConverter;
import id.ezclouds.core.auth.dataobject.EzAuthMemberClientDO;
import id.ezclouds.core.auth.dataobject.EzAuthMemberClientSessionDO;
import id.ezclouds.core.auth.model.CoreAuthAppClient;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;
import id.ezclouds.core.auth.repo.EzAuthAppClientRepository;
import id.ezclouds.core.auth.repo.EzAuthMemberClientRepository;
import id.ezclouds.core.auth.repo.EzAuthMemberClientSessionRepository;
import id.ezclouds.core.auth.request.CoreAppClientAuthRequest;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.core.auth.result.CoreAuthSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthService.java, v 0.1 2024‐01‐07 9:28 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthService {

    @Autowired
    private EzAuthAppClientRepository ezAuthAppClientRepository;

    @Autowired
    private EzAuthMemberClientRepository ezAuthMemberClientRepository;

    @Autowired
    private EzAuthMemberClientSessionRepository ezAuthMemberClientSessionRepository;

    public CoreAuthResult<Void> authAppClient(CoreAppClientAuthRequest request) {
        CoreAuthResult<Void> authResult = new CoreAuthResult<>();

        if (request == null || StringUtil.isBlank(request.getAppId()) || StringUtil.isBlank(request.getClientId()) || StringUtil.isBlank(request.getClientSecret())) {
            return authResult;
        }

        for (CoreAuthAppClient appClient : getActiveAppClients()) {
            boolean appIdMatch = request.getAppId().equals(appClient.getAppId());
            boolean clientIdMatch = request.getClientId().equals(appClient.getClientId());
            boolean clientSecretMatch = request.getClientSecret().equals(appClient.getClientSecret());

            if (appIdMatch && clientIdMatch && clientSecretMatch) {
                authResult.setSuccess(true);
            }
        }

        return authResult;
    }

    public CoreAuthResult<CoreAuthSessionInfo> authMemberClient(String orgId, String appId, String loginType, String loginId, String loginPass, String deviceId) {
        CoreAuthResult<CoreAuthSessionInfo> authResult = new CoreAuthResult<>();

        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository.findByLoginRequest(orgId, appId, loginType, loginId);
        if (memberClientDO == null) {
            authResult.setEzErrorCode(EzErrorCode.MEMBER_CLIENT_NOT_FOUND);
            return authResult;
        }

        int clientStatus = memberClientDO.getStatus();
        if (clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_NOT_ACTIVE) {
            authResult.setEzErrorCode(EzErrorCode.MEMBER_CLIENT_NOT_ACTIVE);
            return authResult;
        }
        if (clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_FROZEN) {
            authResult.setEzErrorCode(EzErrorCode.MEMBER_CLIENT_FROZEN);
            return authResult;
        }
        if (clientStatus != CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE) {
            authResult.setEzErrorCode(EzErrorCode.MEMBER_CLIENT_ABNORMAL);
            return authResult;
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean isPassMatch = bCryptPasswordEncoder.matches(loginPass, memberClientDO.getLoginPassword());
        if (!isPassMatch) {
            authResult.setEzErrorCode(EzErrorCode.MEMBER_LOGIN_FAILED);
            return authResult;
        }

        CoreAuthMemberClient memberClient = CoreAuthModelConverter.convert(memberClientDO);
        EzAuthMemberClientSessionDO sessionDO = startMemberClientSession(memberClient, deviceId);

        CoreAuthSessionInfo sessionInfo = new CoreAuthSessionInfo();
        sessionInfo.setSessionId(sessionDO.getSessionId());
        sessionInfo.setMemberId(memberClientDO.getMemberId());
        sessionInfo.setExpiryTime(sessionDO.getExpiryTime());

        authResult.setSuccess(true);
        authResult.setData(sessionInfo);
        return authResult;
    }

    @Transactional
    public EzAuthMemberClientSessionDO startMemberClientSession(CoreAuthMemberClient memberClient, String deviceId) {
        boolean allowMultipleSession = CoreAuthConfig.MemberClient.allowMultipleAuthSession;
        if (!allowMultipleSession) {
            List<EzAuthMemberClientSessionDO> activeSessions = ezAuthMemberClientSessionRepository.findAllByClientId(memberClient.getOrgId(), memberClient.getClientId());
            activeSessions.forEach(session -> session.setStatus(CoreAuthConstant.MEMBER_CLIENT_STATUS_NOT_ACTIVE));
            ezAuthMemberClientSessionRepository.saveAllAndFlush(activeSessions);
        }

        String currentDateTime = DateUtil.getCurrentFormattedDate();
        String sessionId = HashUtil.createHash(memberClient.getOrgId(), memberClient.getAppId(), memberClient.getClientId(), currentDateTime);

        EzAuthMemberClientSessionDO sessionDO = new EzAuthMemberClientSessionDO();
        sessionDO.setSessionId(sessionId);
        sessionDO.setOrgId(memberClient.getOrgId());
        sessionDO.setShard(ShardUtil.getShardId(memberClient.getMemberId()));
        sessionDO.setAppId(memberClient.getAppId());
        sessionDO.setClientId(memberClient.getClientId());
        sessionDO.setMemberId(memberClient.getMemberId());
        sessionDO.setDeviceId(deviceId);
        sessionDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
        sessionDO.setStatus(CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE);

        Date expiryDate = DateUtil.getDateAfterDays(new Date(), CoreAuthConfig.MemberClient.sessionExpiryDays);
        sessionDO.setExpiryTime(DateUtil.getFormattedDate(expiryDate));

        ezAuthMemberClientSessionRepository.save(sessionDO);

        return sessionDO;
    }

    public void createMemberClient(CoreAuthMemberClient memberClient) {
        EzAuthMemberClientDO memberClientDO = CoreAuthModelConverter.convert(memberClient);
        memberClientDO.setClientId(HashUtil.createHash(memberClient.getLoginType(), memberClient.getMemberId()));
        memberClientDO.setCreatedTime(DateUtil.getCurrentFormattedDate());

        ezAuthMemberClientRepository.save(memberClientDO);
    }

    public CoreAuthMemberClient getOptimisticMemberClient(String loginType, String memberId) {
        String clientId = HashUtil.createHash(loginType, memberId);
        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository.findById(clientId).orElse(null);
        AssertUtil.notNull(memberClientDO, EzErrorCode.MEMBER_CLIENT_NOT_FOUND, "Member client not found");
        return CoreAuthModelConverter.convert(memberClientDO);
    }

    @Cacheable("core_auth_app_client")
    public List<CoreAuthAppClient> getActiveAppClients() {
        return ezAuthAppClientRepository
                .findActiveAppClients()
                .stream()
                .map(CoreAuthModelConverter::convert)
                .collect(Collectors.toList());
    }
}