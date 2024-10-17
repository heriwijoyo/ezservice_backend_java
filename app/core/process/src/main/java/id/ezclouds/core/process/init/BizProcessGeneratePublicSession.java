/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.init;

import id.ezclouds.common.facade.auth.AuthPublicSessionService;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessGeneratePublicSession.java, v 0.1 2024‐10‐10 12:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessGeneratePublicSession extends BizAsyncProcessor {

    @Autowired
    private AuthPublicSessionService authPublicSessionService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_PUBLIC_SESSION;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

        authPublicSessionService.generateWebPublicSession(orgId);

        return true;
    }
}