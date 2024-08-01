/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.member.MemberReportService;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.core.process.biz.inner.BizInnerProcessorReportGenerateOverall;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessorReportUpdateMember.java, v 0.1 2024‐08‐01 8:00 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessorReportUpdateMember extends BizAsyncProcessor {

    @Autowired
    private MemberReportService memberReportService;

    @Autowired
    private BizInnerProcessorReportGenerateOverall bizInnerProcessorReportGenerateOverall;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.GENERATE_REPORT_MEMBER_TODAY;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String orgId = (String) request;
        logData.add("ORG_ID="+ orgId);

        long count = memberReportService.countToday(orgId);
        logData.add("COUNT="+ count);

        bizInnerProcessorReportGenerateOverall
                .storeReport(orgId, BizReportOverallKey.MEMBER_TODAY.getCode(), (int)count);

        return true;
    }
}