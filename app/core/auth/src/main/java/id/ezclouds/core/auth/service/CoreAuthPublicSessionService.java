/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.service;

import id.ezclouds.common.facade.auth.AuthPublicSessionService;
import id.ezclouds.common.facade.dal.auth.AuthWebPublicSessionDAO;
import id.ezclouds.common.model.auth.AuthRole;
import id.ezclouds.common.model.auth.AuthScene;
import id.ezclouds.common.model.auth.WebPublicSession;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAuthPublicSessionService.java, v 0.1 2024‐10‐10 1:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAuthPublicSessionService implements AuthPublicSessionService {

    @Autowired
    private AuthWebPublicSessionDAO authWebPublicSessionDAO;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public String generateWebPublicSession(String orgId) {
        Date currentDate = new Date();
        Date expiryDate = DateUtil.getDateAfterMins(currentDate, 7200);

        int sessionCodeNumber = new Random().nextInt(900000) + 100000;
        String sessionCode = String.valueOf(sessionCodeNumber);

        WebPublicSession webPublicSession = new WebPublicSession();
        webPublicSession.setSessionId(HashUtil.createHash(sessionCode));
        webPublicSession.setOrgId(orgId);
        webPublicSession.setAuthScene(AuthScene.WEB_PUBLIC_SESSION);
        webPublicSession.setSessionCode(sessionCode);
        webPublicSession.setAuthRoles(Collections.singletonList(AuthRole.PUBLIC_ACCESS));
        webPublicSession.setCreatedTime(DateUtil.getFormattedDate(currentDate));
        webPublicSession.setExpiryTime(DateUtil.getFormattedDate(expiryDate));

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                authWebPublicSessionDAO.store(webPublicSession);
            }
        });

        return webPublicSession.getSessionId();
    }
}