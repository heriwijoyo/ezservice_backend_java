/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service.inner;

import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.auth.constant.CoreAuthConstant;
import id.ezclouds.core.auth.dataobject.EzAuthAdminCommonSessionDO;
import id.ezclouds.core.auth.repo.EzAuthAdminCommonSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: InnerAuthService.java, v 0.1 2024‐02‐10 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class InnerAuthService {

    @Autowired
    private EzAuthAdminCommonSessionRepository ezAuthAdminCommonSessionRepository;

    @Transactional
    public void adminCreateSession(EzAuthAdminCommonSessionDO sessionDO) {
        ezAuthAdminCommonSessionRepository.saveAndFlush(sessionDO);
    }

    public List<EzAuthAdminCommonSessionDO> getAdminSession(String orgId, String memberId) {
        return ezAuthAdminCommonSessionRepository.fetchByMemberId(orgId, memberId);
    }

    public String adminLoginBySessionCode(String sessionCode) throws Exception {
        EzAuthAdminCommonSessionDO sessionDO = ezAuthAdminCommonSessionRepository
                .findBySessionCode(sessionCode);
        AssertUtil.notNull(sessionDO, EzErrorCode.SESSION_CODE_INVALID);
        AssertUtil.isTrue(sessionDO.getStatus() == CoreAuthConstant.Status.ACTIVE, EzErrorCode.SESSION_CODE_INVALID);

        Date currentDate = new Date();
        Date expiryDate = DateUtil.parseFormattedDate(sessionDO.getExpiryTime());
        AssertUtil.isTrue(expiryDate.getTime() > currentDate.getTime(), EzErrorCode.SESSION_CODE_INVALID);

        return sessionDO.getSessionId();
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
}