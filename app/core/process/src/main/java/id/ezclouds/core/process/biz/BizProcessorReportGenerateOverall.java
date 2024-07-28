/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessorReportGenerateOverall.java, v 0.1 2024‐07‐28 4:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessorReportGenerateOverall extends BizAsyncProcessor {

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_OVERALL;
    }

    @Override
    protected int maxProcessTime() {
        return 1 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);


        return true;
    }
}