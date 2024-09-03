/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.model.util.CoreAreaUtil;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessMasterDataAreaInitialize.java, v 0.1 2024‐09‐01 9:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizProcessMasterDataAreaInitialize extends BizAsyncProcessor {

    @Autowired
    private CoreConfigService coreConfigService;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.MASTER_DATA_AREA_INIT;
    }

    @Override
    protected int maxProcessTime() {
        return PROCESS_TIME_MINUTE_10;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String paramRequest = (String) request;
        String orgId = paramRequest.split(",")[0];
        String targetLevel = paramRequest.split(",")[1];
        CoreAreaLevel targetAreaLevel = CoreAreaLevel.getByCode(targetLevel);

        String areaRootLevel = coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.CORE_AREA_LEVEL_ROOT)
                .getConfigValue();

        String areaRootIds = coreConfigService
                .getOrgConfig(orgId, CoreOrgConfigType.CORE_AREA_ROOT_IDS)
                .getConfigValue();






        return false;
    }

    private void recursiveLoadAndExecute(CoreAreaLevel currentLevel, String currentLevelId, CoreAreaLevel targetLevel) {
        if (currentLevel == targetLevel) {
            // execute initiation
        } else {
            currentLevel = CoreAreaUtil.getLowerLevel(currentLevel);

        }
    }
}