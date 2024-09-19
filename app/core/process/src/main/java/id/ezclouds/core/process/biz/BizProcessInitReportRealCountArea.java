/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.area.CoreAreaScanListener;
import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.process.biz.inner.BizInnerProcessRealCountInitialize;
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
    private CoreWorkingAreaService coreWorkingAreaService;

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

        CoreAreaLevel targetAreaLevel = CoreAreaLevel.getByCode(targetLevel);
        AssertUtil.notNull(targetAreaLevel, EzErrorCode.ILLEGAL_PARAM);

        coreWorkingAreaService
                .scanWorkingAreaRecursive(orgId, targetAreaLevel, new CoreAreaScanListener() {
                    @Override
                    public void areaOnTargetLevel(CoreArea currentArea) {
                        bizInnerProcessRealCountInitialize
                                .init(orgId, targetLevel, currentArea.getName(), currentArea.getParentId(), 0);
                    }
                });

        return true;
    }
}