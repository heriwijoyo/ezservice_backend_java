/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service.inner;

import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.auth.constant.CoreAuthConstant;
import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.model.CoreAuthAdminScene;
import id.ezclouds.core.auth.repo.EzAuthAdminCommonSessionRepository;
import id.ezclouds.core.shared.constant.CoreConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AuthInnerService.java, v 0.1 2024‐02‐10 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AuthInnerService {

    @Autowired
    private EzAuthAdminCommonSessionRepository ezAuthAdminCommonSessionRepository;

    @Autowired
    private CoreConfigService coreConfigService;

    @Transactional
    public void adminCreateSession(EzAuthAdminCommonSessionDO sessionDO) {
        ezAuthAdminCommonSessionRepository.saveAndFlush(sessionDO);
    }

    public List<EzAuthAdminCommonSessionDO> getAdminSession(String orgId, String memberId) {
        return ezAuthAdminCommonSessionRepository.fetchByMemberId(orgId, memberId);
    }

    @Transactional
    public String adminLoginBySessionCode(String sessionCode) throws Exception {
        EzAuthAdminCommonSessionDO sessionDO = ezAuthAdminCommonSessionRepository
                .findBySessionCode(sessionCode);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_CODE_INVALID);
        AssertUtil.isTrue(sessionDO.getStatus() == CoreAuthConstant.Status.ACTIVE, EzErrorCode.SESSION_CODE_INVALID);

        Date currentDate = new Date();
        Date expiryDate = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        AssertUtil.isTrue(expiryDate.getTime() > currentDate.getTime(), EzErrorCode.SESSION_CODE_INVALID);

        sessionDO.setLoginTime(DateUtil.getFormattedDate(currentDate));
        sessionDO.setStatus(CoreAuthConstant.Status.NOT_ACTIVE);
        ezAuthAdminCommonSessionRepository.saveAndFlush(sessionDO);

        return sessionDO.getSessionId();
    }

    @Transactional
    public String adminValidateSessionId(String sessionId) {
        EzAuthAdminCommonSessionDO sessionDO = ezAuthAdminCommonSessionRepository
                .findById(sessionId)
                .orElse(null);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_INVALID);

        Date currentDate = new Date();
        Date expiryDate = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        if (currentDate.getTime() >= expiryDate.getTime()) {
            ezAuthAdminCommonSessionRepository.delete(sessionDO);
            ezAuthAdminCommonSessionRepository.flush();
            throw new EzErrorException(EzErrorCode.SESSION_INVALID);
        }

        int expiryExtensionMins = getAdminCommonSessionExpMins(sessionDO.getOrgId());
        Date newExpiryDate = DateUtil.getDateAfterMins(currentDate, expiryExtensionMins);
        sessionDO.setExpiryTime(DateUtil.getFormattedDate(newExpiryDate));
        ezAuthAdminCommonSessionRepository.saveAndFlush(sessionDO);

        return sessionDO.getSessionId();
    }

    public EzAuthAdminCommonSessionDO authGetAndTouch(String sessionId) {
        EzAuthAdminCommonSessionDO sessionDO = ezAuthAdminCommonSessionRepository
                .findById(sessionId)
                .orElse(null);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_INVALID);

        Date currentDate = new Date();
        Date expiryDate = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        if (currentDate.getTime() >= expiryDate.getTime()) {
            ezAuthAdminCommonSessionRepository.delete(sessionDO);
            ezAuthAdminCommonSessionRepository.flush();
            throw new EzErrorException(EzErrorCode.SESSION_INVALID);
        }

        int expiryExtensionMins;
        if (CoreAuthAdminScene.WEB_PUBLIC_SESSION.getCode().equals(sessionDO.getScene())) {
            expiryExtensionMins = CoreAuthConstant.PUBLIC_SESSION_EXPIRY_MINS;
        } else {
            expiryExtensionMins = getAdminCommonSessionExpMins(sessionDO.getOrgId());
        }
        Date newExpiryDate = DateUtil.getDateAfterMins(currentDate, expiryExtensionMins);
        sessionDO.setExpiryTime(DateUtil.getFormattedDate(newExpiryDate));
        ezAuthAdminCommonSessionRepository.saveAndFlush(sessionDO);

        return sessionDO;
    }

    @Transactional
    public void adminLogoutSession(String sessionId) {
        EzAuthAdminCommonSessionDO sessionDO = ezAuthAdminCommonSessionRepository
                .findById(sessionId)
                .orElse(null);

        if (sessionDO != null) {
            ezAuthAdminCommonSessionRepository.delete(sessionDO);
            ezAuthAdminCommonSessionRepository.flush();
        }
    }

    public int getAdminCommonSessionExpMins(String orgId) {
        //TODO: update logic with proper config
        if (CoreConstant.SU_ORG_ID.equals(orgId)) {
            return 30;
        }
        return coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.ADMIN_COMMON_SESSION_EXPIRY_MINS)
                .getIntValue();
    }
}