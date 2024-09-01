/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.core.process.model.BizProcessEvent;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessMasterDataAreaInitialize.java, v 0.1 2024‐09‐01 9:12 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizProcessMasterDataAreaInitialize extends BizAsyncProcessor {

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
        return false;
    }
}