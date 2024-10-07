/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.area.CoreAreaScanListener;
import id.ezclouds.common.facade.area.CoreAreaService;
import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.area.CoreAreaRecursive;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.core.process.biz.inner.BizInnerProcessMasterDataAreaInitialize;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessInitMasterDataArea.java, v 0.1 2024‐09‐01 9:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessInitMasterDataArea extends BizAsyncProcessor {

    @Autowired
    private CoreConfigService coreConfigService;

    @Autowired
    private CoreAreaService coreAreaService;

    @Autowired
    private CoreWorkingAreaService coreWorkingAreaService;

    @Autowired
    private BizInnerProcessMasterDataAreaInitialize bizInnerProcessMasterDataAreaInitialize;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.MASTER_DATA_INIT_AREA;
    }

    @Override
    protected int maxProcessTime() {
        return PROCESS_TIME_MINUTE_10;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        AssertUtil.isTrue((request instanceof String), EzErrorCode.ILLEGAL_PARAM);
        String paramRequest = (String) request;
        String[] paramSplit = paramRequest.split(",");
        AssertUtil.isTrue(paramSplit.length >= 3, EzErrorCode.ILLEGAL_PARAM);

        String orgId = paramRequest.split(",")[0];
        String scene = paramRequest.split(",")[1];
        String targetLevel = paramRequest.split(",")[2];
        logData.add("ORG_ID="+ orgId +",SCENE="+ scene + ",TARGET_LEVEL="+ targetLevel);

        bizInnerProcessMasterDataAreaInitialize.initOverall(orgId);

        CoreAreaLevel targetAreaLevel = CoreAreaLevel.getByCode(targetLevel);
        AssertUtil.notNull(targetAreaLevel, EzErrorCode.ILLEGAL_PARAM);

        coreWorkingAreaService
                .scanWorkingAreaRecursive(orgId, targetAreaLevel, new CoreAreaScanListener() {
                    @Override
                    public void areaOnTargetLevel(CoreAreaRecursive areaRecursive) {
                        bizInnerProcessMasterDataAreaInitialize.init(orgId, scene, areaRecursive);
                    }
                });

        return true;
    }
}