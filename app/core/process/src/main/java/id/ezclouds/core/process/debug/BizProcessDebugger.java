/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.debug;

import id.ezclouds.common.facade.biz.BizReportRealtimeService;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessDebugger.java, v 0.1 2024‐09‐09 12:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessDebugger extends BizAsyncProcessor {

    @Autowired
    private BizReportRealtimeService bizReportRealtimeService;

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {

        System.out.println(DateUtil.getCurrentFormattedDateMillis());

        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bizReportRealtimeService.accumulateValue("RJL0", "DEBUG", 1);
                }
            }).start();
        }
        return false;
    }

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.BIZ_DEBUGGER;
    }
}