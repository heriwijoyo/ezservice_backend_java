/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.core.process.biz.inner.BizInnerProcessReportOverall;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessInitReportOverall.java, v 0.1 2024‐09‐17 3:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessInitReportOverall extends BizAsyncProcessor {

    @Autowired
    private BizInnerProcessReportOverall bizInnerProcessReportOverall;

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
        String orgId = (String) request;

        for (BizReportOverallKey overallKey : BizReportOverallKey.values()) {
            bizInnerProcessReportOverall.init(orgId, overallKey.getCode());
        }

        return true;
    }
}