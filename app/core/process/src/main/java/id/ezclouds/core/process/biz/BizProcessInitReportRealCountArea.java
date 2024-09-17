/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.core.process.biz.inner.BizInnerProcessAreaInitialize;
import id.ezclouds.core.process.biz.inner.BizInnerProcessRealCountInitialize;
import id.ezclouds.core.process.model.AreaInitConfig;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessInitReportRealCountArea.java, v 0.1 2024‐09‐10 11:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessInitReportRealCountArea extends BizAsyncProcessor {

    @Autowired
    private BizInnerProcessAreaInitialize bizInnerProcessAreaInitialize;

    @Autowired
    private BizInnerProcessRealCountInitialize bizInnerProcessRealCountInitialize;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.REPORT_DATA_INIT;
    }

    @Override
    protected int maxProcessTime() {
        return 30 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String param = (String) request;
        String orgId = param.split(",")[0];
        String targetLevel = param.split(",")[1];
        logData.add("ORG_ID="+ orgId +",TARGET_LEVEL="+ targetLevel);

        bizInnerProcessAreaInitialize.setAreaOnTargetCallback(currentArea -> {
            bizInnerProcessRealCountInitialize
                    .init(orgId, targetLevel, currentArea.getName(), currentArea.getParentId(), 0);
        });

        AreaInitConfig areaInitConfig = bizInnerProcessAreaInitialize
                .getAreaInitConfig(orgId, targetLevel);

        bizInnerProcessAreaInitialize
                .startInitArea(areaInitConfig);

        return true;
    }
}