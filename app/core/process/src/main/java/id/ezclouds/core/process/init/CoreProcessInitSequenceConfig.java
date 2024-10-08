/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.init;

import id.ezclouds.common.facade.core.CoreSequenceService;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreProcessInitSequenceConfig.java, v 0.1 2024‐10‐08 11:57 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreProcessInitSequenceConfig extends BizAsyncProcessor {

    @Autowired
    private CoreSequenceService coreSequenceService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.INIT_SEQUENCE_CONFIG;
    }

    @Override
    protected int maxProcessTime() {
        return 30 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

        coreSequenceService.initSequenceConfig(orgId);

        return true;
    }
}