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
import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.dataobject.EzAuthMemberClientDO;
import id.ezclouds.core.auth.dataobject.EzAuthMemberClientSessionDO;
import id.ezclouds.core.auth.dataobject.EzAuthMemberCommonSessionDO;
import id.ezclouds.core.auth.model.CoreAuthAdminSession;
import id.ezclouds.core.auth.model.CoreAuthAppClient;
import id.ezclouds.core.auth.model.CoreAuthMemberClient;
import id.ezclouds.core.auth.repo.EzAuthAppClientRepository;
import id.ezclouds.core.auth.repo.EzAuthMemberClientRepository;
import id.ezclouds.core.auth.repo.EzAuthMemberClientSessionRepository;
import id.ezclouds.core.auth.repo.EzAuthMemberCommonSessionRepository;
import id.ezclouds.core.auth.request.CoreAdminCommonSessionCreateRequest;
import id.ezclouds.core.auth.request.CoreAppClientAuthRequest;
import id.ezclouds.core.auth.request.CoreMemberClientAuthRequest;
import id.ezclouds.core.auth.request.CoreMemberCommonSessionRequest;
import id.ezclouds.core.auth.result.CoreCommonSession;
import id.ezclouds.core.auth.result.CoreAuthMemberSessionInfo;
import id.ezclouds.core.auth.result.CoreAuthResult;
import id.ezclouds.core.auth.service.inner.InnerAuthService;
import id.ezclouds.core.shared.service.CoreConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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

    @Autowired
    private EzAuthMemberCommonSessionRepository ezAuthMemberCommonSessionRepository;

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private InnerAuthService innerAuthService;

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

    public CoreAuthMemberSessionInfo authMemberClient(CoreMemberClientAuthRequest request) throws Exception {
        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository
                .findByLoginRequest(request.getOrgId(), request.getAppId(), request.getLoginType(), request.getLoginId());
        AssertUtil.notNull(memberClientDO, EzErrorCode.MEMBER_CLIENT_NOT_FOUND);

        int clientStatus = memberClientDO.getStatus();
        AssertUtil.isNotTrue(clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_NOT_ACTIVE, EzErrorCode.MEMBER_CLIENT_NOT_ACTIVE);
        AssertUtil.isNotTrue(clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_FROZEN, EzErrorCode.MEMBER_CLIENT_FROZEN);
        AssertUtil.isTrue(clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE, EzErrorCode.MEMBER_CLIENT_ABNORMAL);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean isPassMatch = bCryptPasswordEncoder.matches(request.getLoginPass(), memberClientDO.getLoginPassword());
        AssertUtil.isTrue(isPassMatch, EzErrorCode.MEMBER_LOGIN_FAILED);

        EzAuthMemberClientSessionDO sessionDO = startMemberClientSession(memberClientDO, request.getDeviceId());

        CoreAuthMemberSessionInfo sessionInfo = new CoreAuthMemberSessionInfo();
        sessionInfo.setSessionId(sessionDO.getSessionId());
        sessionInfo.setMemberId(memberClientDO.getMemberId());
        sessionInfo.setClientId(memberClientDO.getClientId());
        return sessionInfo;
    }

    @Transactional
    public EzAuthMemberClientSessionDO startMemberClientSession(EzAuthMemberClientDO memberClientDO, String deviceId) {
        String configValue = coreConfigService.getConfigValue(
                CoreAuthConfig.Key.MEMBER_CLIENT_ALLOW_MULTIPLE_SESSION,
                memberClientDO.getOrgId()
        );
        boolean allowMultipleSession = Boolean.parseBoolean(configValue);

        if (!allowMultipleSession) {
            List<EzAuthMemberClientSessionDO> activeSessions = ezAuthMemberClientSessionRepository
                    .findAllActiveByClientId(memberClientDO.getOrgId(), memberClientDO.getClientId());
            activeSessions.forEach(session -> session.setStatus(CoreAuthConstant.MEMBER_CLIENT_STATUS_NOT_ACTIVE));
            ezAuthMemberClientSessionRepository.saveAllAndFlush(activeSessions);
        }

        String currentDateTime = DateUtil.getCurrentFormattedDate();
        String sessionId = HashUtil.createHash(
                memberClientDO.getOrgId(),
                memberClientDO.getAppId(),
                memberClientDO.getClientId(),
                memberClientDO.getMemberId(),
                currentDateTime
        );

        EzAuthMemberClientSessionDO sessionDO = new EzAuthMemberClientSessionDO();
        sessionDO.setSessionId(sessionId);
        sessionDO.setOrgId(memberClientDO.getOrgId());
        sessionDO.setShard(ShardUtil.getShardId(memberClientDO.getMemberId()));
        sessionDO.setAppId(memberClientDO.getAppId());
        sessionDO.setClientId(memberClientDO.getClientId());
        sessionDO.setMemberId(memberClientDO.getMemberId());
        sessionDO.setDeviceId(deviceId);
        sessionDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
        sessionDO.setStatus(CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE);

        Date expiryDate = DateUtil.getDateAfterDays(new Date(), getMemberClientSessionExpDays(memberClientDO.getOrgId()));
        sessionDO.setExpiryTime(DateUtil.getFormattedDate(expiryDate));

        ezAuthMemberClientSessionRepository.save(sessionDO);

        return sessionDO;
    }

    @Transactional
    public void updateMemberSessionRoles(String sessionId, String memberRoles) {
        EzAuthMemberClientSessionDO sessionDO = ezAuthMemberClientSessionRepository
                .findById(sessionId).orElse(null);
        if (sessionDO != null && StringUtil.isNotBlank(memberRoles)) {
            sessionDO.setMemberRoles(memberRoles);
            ezAuthMemberClientSessionRepository.saveAndFlush(sessionDO);
        }
    }

    @Transactional
    public CoreCommonSession createMemberCommonSession(CoreMemberCommonSessionRequest request) throws Exception {
        EzAuthMemberClientDO memberClientDO = ezAuthMemberClientRepository.findByLoginRequest(request.getOrgId(), request.getAppId(), request.getLoginType(), request.getLoginId());
        AssertUtil.notNull(memberClientDO, EzErrorCode.MEMBER_CLIENT_NOT_FOUND);

        int clientStatus = memberClientDO.getStatus();
        AssertUtil.isNotTrue(clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_NOT_ACTIVE, EzErrorCode.MEMBER_CLIENT_NOT_ACTIVE);
        AssertUtil.isNotTrue(clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_FROZEN, EzErrorCode.MEMBER_CLIENT_FROZEN);
        AssertUtil.isTrue(clientStatus == CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE, EzErrorCode.MEMBER_CLIENT_ABNORMAL);

        List<EzAuthMemberCommonSessionDO> activeSessionDOs = new ArrayList<>();
        List<EzAuthMemberCommonSessionDO> inActiveSessionDOs = new ArrayList<>();
        List<EzAuthMemberCommonSessionDO> sessionDOs = ezAuthMemberCommonSessionRepository.findBySceneLoginId(
                request.getOrgId(),
                request.getScene(),
                request.getVerifyStrategy(),
                memberClientDO.getMemberId()
        );
        sessionDOs.forEach(sessionDO -> {
            if (sessionDO.getStatus() == CoreAuthConstant.Status.NOT_ACTIVE || isSessionExpired(sessionDO)) {
                inActiveSessionDOs.add(sessionDO);
            } else {
                activeSessionDOs.add(sessionDO);
            }
        });
        EzAuthMemberCommonSessionDO activeSessionDO = null;
        if (activeSessionDOs.size() > 0) {
            for (EzAuthMemberCommonSessionDO activeDO : activeSessionDOs) {
                if (activeSessionDO == null) {
                    activeSessionDO = activeDO;
                } else {
                    Date activeDate = DateUtil.parseFormattedDate(activeSessionDO.getExpiryTime());
                    Date compareDate = DateUtil.parseFormattedDate(activeDO.getExpiryTime());
                    if (compareDate.getTime() > activeDate.getTime()) {
                        inActiveSessionDOs.add(activeSessionDO);
                        activeSessionDO = activeDO;
                    }
                    else {
                        inActiveSessionDOs.add(activeDO);
                    }
                }
            }
        }

        if (inActiveSessionDOs.size() > 0) {
            inActiveSessionDOs.forEach(sessionDO -> {
                ezAuthMemberCommonSessionRepository.delete(sessionDO);
            });
        }

        EzAuthMemberCommonSessionDO sessionDO;
        if (activeSessionDO != null) {
            sessionDO = activeSessionDO;
        } else {
            String currentDateTime = DateUtil.getCurrentFormattedDate();
            String sessionId = HashUtil.createHash(
                    memberClientDO.getOrgId(),
                    memberClientDO.getClientId(),
                    request.getScene(),
                    request.getVerifyStrategy(),
                    currentDateTime
            );
            int verifyCodeNumber = new Random().nextInt(9000) + 1000;
            String verifyCode = String.valueOf(verifyCodeNumber);

            sessionDO = new EzAuthMemberCommonSessionDO();
            sessionDO.setSessionId(sessionId);
            sessionDO.setOrgId(memberClientDO.getOrgId());
            sessionDO.setShard(ShardUtil.getShardId(memberClientDO.getMemberId()));
            sessionDO.setScene(request.getScene());
            sessionDO.setVerifyStrategy(request.getVerifyStrategy());
            sessionDO.setVerifyCode(verifyCode);
            sessionDO.setAppId(memberClientDO.getAppId());
            sessionDO.setClientId(memberClientDO.getClientId());
            sessionDO.setMemberId(memberClientDO.getMemberId());
            sessionDO.setDeviceId(request.getDeviceId());
            sessionDO.setCreatedTime(DateUtil.getCurrentFormattedDate());
            sessionDO.setStatus(CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE);

            int expiryMins = getMemberCommonSessionExpMins(memberClientDO.getOrgId());
            Date expiryDate = DateUtil.getDateAfterMins(new Date(), expiryMins);
            sessionDO.setExpiryTime(DateUtil.getFormattedDate(expiryDate));

            ezAuthMemberCommonSessionRepository.saveAndFlush(sessionDO);
        }

        CoreCommonSession sessionInfo = new CoreCommonSession();
        sessionInfo.setSessionId(sessionDO.getSessionId());
        sessionInfo.setScene(request.getScene());
        sessionInfo.setVerifyStrategy(request.getVerifyStrategy());
        sessionInfo.setVerifyTarget(request.getLoginId());
        sessionInfo.setVerifyCode(sessionDO.getVerifyCode());
        sessionInfo.setExpiryTime(sessionDO.getExpiryTime());
        return sessionInfo;
    }

    public CoreAuthMemberSessionInfo verifyCommonSession(CoreCommonSession commonSession) throws Exception {
        EzAuthMemberCommonSessionDO sessionDO = ezAuthMemberCommonSessionRepository
                .findById(commonSession.getSessionId())
                .orElse(null);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_INVALID);
        AssertUtil.isTrue(sessionDO.getStatus() == CoreAuthConstant.Status.ACTIVE, EzErrorCode.SESSION_UNAVAILABLE);

        Date sessionExpire = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        AssertUtil.isTrue(DateUtil.getTimeNow() < sessionExpire.getTime(), EzErrorCode.SESSION_EXPIRED);
        AssertUtil.isTrue(StringUtil.equalsNotNull(sessionDO.getVerifyCode(), commonSession.getVerifyCode()), EzErrorCode.SESSION_VERIFY_FAILED);

        CoreAuthMemberSessionInfo sessionInfo = new CoreAuthMemberSessionInfo();
        sessionInfo.setClientId(sessionDO.getClientId());
        sessionInfo.setMemberId(sessionDO.getMemberId());
        return sessionInfo;
    }

    @Transactional
    public void invalidateCommonSession(CoreCommonSession commonSession) {
        EzAuthMemberCommonSessionDO sessionDO = ezAuthMemberCommonSessionRepository
                .findById(commonSession.getSessionId())
                .orElse(null);
        if (sessionDO != null) {
            sessionDO.setStatus(CoreAuthConstant.Status.NOT_ACTIVE);
            sessionDO.setVerifyTime(DateUtil.getCurrentFormattedDate());
            ezAuthMemberCommonSessionRepository.saveAndFlush(sessionDO);
        }
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

    @Transactional
    public CoreAuthMemberSessionInfo authMemberSession(String sessionId) throws Exception {
        EzAuthMemberClientSessionDO sessionDO = ezAuthMemberClientSessionRepository.findById(sessionId).orElse(null);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_INVALID);
        AssertUtil.isNotTrue(sessionDO.getStatus() < CoreAuthConstant.MEMBER_CLIENT_STATUS_ACTIVE, EzErrorCode.SESSION_EXPIRED);

        Date newExpiry = DateUtil.getDateAfterDays(new Date(), getMemberClientSessionExpDays(sessionDO.getOrgId()));
        sessionDO.setExpiryTime(DateUtil.getFormattedDate(newExpiry));

        ezAuthMemberClientSessionRepository.saveAndFlush(sessionDO);

        CoreAuthMemberSessionInfo sessionInfo = new CoreAuthMemberSessionInfo();
        sessionInfo.setMemberId(sessionDO.getMemberId());
        sessionInfo.setMemberRoles(sessionDO.getMemberRoles());
        sessionInfo.setSessionId(sessionDO.getSessionId());
        sessionInfo.setClientId(sessionDO.getClientId());

        return sessionInfo;
    }

    public CoreAuthMemberSessionInfo authCommonSession(String sessionId) throws Exception {
        EzAuthMemberCommonSessionDO sessionDO = ezAuthMemberCommonSessionRepository
                .findById(sessionId)
                .orElse(null);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_INVALID);
        AssertUtil.isTrue(sessionDO.getStatus() == CoreAuthConstant.Status.NOT_ACTIVE, EzErrorCode.SESSION_INVALID);
        AssertUtil.notBlank(sessionDO.getVerifyTime(), EzErrorCode.SESSION_INVALID);

        Date expiryDate = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        AssertUtil.isTrue(expiryDate.getTime() > DateUtil.getTimeNow(), EzErrorCode.SESSION_EXPIRED);

        CoreAuthMemberSessionInfo sessionInfo = new CoreAuthMemberSessionInfo();
        sessionInfo.setMemberId(sessionDO.getMemberId());
        sessionInfo.setSessionId(sessionDO.getSessionId());
        sessionInfo.setClientId(sessionDO.getClientId());

        return sessionInfo;
    }

    @Transactional
    public CoreAuthResult<Void> invalidateMemberSession(String sessionId) {
        CoreAuthResult<Void> authResult = new CoreAuthResult<>();

        EzAuthMemberClientSessionDO sessionDO = ezAuthMemberClientSessionRepository.findById(sessionId).orElse(null);

        if (sessionDO != null) {
            sessionDO.setStatus(CoreAuthConstant.MEMBER_CLIENT_STATUS_NOT_ACTIVE);
            ezAuthMemberClientSessionRepository.saveAndFlush(sessionDO);
        }

        authResult.setSuccess(true);
        return authResult;
    }

    @Transactional
    public void updateMemberClientPassword(String clientId, String newPassword) throws Exception {
        EzAuthMemberClientDO clientDO = ezAuthMemberClientRepository
                .findById(clientId)
                .orElse(null);
        AssertUtil.notNull(clientDO, EzErrorCode.MEMBER_CLIENT_NOT_FOUND);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newEncryptPass = bCryptPasswordEncoder.encode(newPassword);
        clientDO.setLoginPassword(newEncryptPass);

        ezAuthMemberClientRepository.saveAndFlush(clientDO);
    }

    public CoreAuthAdminSession adminCreateSession(CoreAdminCommonSessionCreateRequest request) throws Exception {
        EzAuthAdminCommonSessionDO sessionDO = null;

        int maxRetry = 10;
        int retryCount = 0;
        boolean createError = false;

        while (retryCount < 1 || (createError && (retryCount < maxRetry))) {
            try {
                Date currentDate = new Date();
                Date expiryDate = DateUtil.getDateAfterMins(currentDate, getAdminCommonSessionExpMins(request.getOrgId()));

                int sessionCodeNumber = new Random().nextInt(900000) + 100000;
                String sessionCode = String.valueOf(sessionCodeNumber);
                String sesionId = HashUtil.createHash(sessionCode);

                sessionDO = new EzAuthAdminCommonSessionDO();
                sessionDO.setSessionId(sesionId);
                sessionDO.setSessionCode(sessionCode);
                sessionDO.setScene(request.getScene());
                sessionDO.setOrgId(request.getOrgId());
                sessionDO.setOrgCode(request.getOrgCode());
                sessionDO.setAppId(request.getAppId());
                sessionDO.setClientId(request.getClientId());
                sessionDO.setDeviceId(request.getDeviceId());
                sessionDO.setMemberId(request.getMemberId());
                sessionDO.setMemberRoles(request.getMemberRoles());
                sessionDO.setCreatedTime(DateUtil.getFormattedDate(currentDate));
                sessionDO.setExpiryTime(DateUtil.getFormattedDate(expiryDate));
                sessionDO.setStatus(CoreAuthConstant.Status.ACTIVE);

                innerAuthService.adminCreateSession(sessionDO);
            } catch (Exception e) {
                createError = true;
            }
            retryCount++;
        }

        if (sessionDO != null) {
            return CoreAuthModelConverter.convert(sessionDO);
        }
        return null;
    }

    public List<CoreAuthAdminSession> adminGetSession(String orgId, String memberId) {
        return innerAuthService
                .getAdminSession(orgId, memberId)
                .stream()
                .map(CoreAuthModelConverter::convert)
                .collect(Collectors.toList());
    }

    public void adminLogoutSession(String sessionId) {
        innerAuthService.adminLogoutSession(sessionId);
    }

    @Cacheable("core_auth_app_client")
    public List<CoreAuthAppClient> getActiveAppClients() {
        return ezAuthAppClientRepository
                .findActiveAppClients()
                .stream()
                .map(CoreAuthModelConverter::convert)
                .collect(Collectors.toList());
    }

    private int getMemberClientSessionExpDays(String orgId) {
        String expDays = coreConfigService.getConfigValue(CoreAuthConfig.Key.MEMBER_CLIENT_SESSION_EXPIRY_DAYS, orgId);
        return Integer.parseInt(expDays);
    }

    private int getMemberCommonSessionExpMins(String orgId) {
        String expMins = coreConfigService.getConfigValue(CoreAuthConfig.Key.MEMBER_COMMON_SESSION_EXPIRY_MINS, orgId);
        return Integer.parseInt(expMins);
    }

    private int getAdminCommonSessionExpMins(String orgId) {
        String expMins = coreConfigService.getConfigValue(CoreAuthConfig.Key.ADMIN_COMMON_SESSION_EXPIRY_MINS, orgId);
        return Integer.parseInt(expMins);
    }


    private boolean isSessionExpired(EzAuthMemberCommonSessionDO sessionDO) {
        Date sessionExpDate = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        return sessionExpDate.getTime() <= DateUtil.getTimeNow();
    }
}