/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.scheduler;

import id.ezclouds.biz.ezservice.enums.BizSchedulerScene;
import id.ezclouds.biz.ezservice.service.processor.shared.BizThreadSharedResource;
import id.ezclouds.biz.ezservice.service.processor.event.BizProcessEvent;
import id.ezclouds.biz.ezservice.service.processor.BizSyncMemberUnionProcessor;
import id.ezclouds.biz.ezservice.service.processor.*;
import id.ezclouds.biz.ezservice.service.processor.sample.BizSampleOneProcessor;
import id.ezclouds.biz.ezservice.service.processor.sample.BizSampleTwoProcessor;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizSchedulerTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSchedulerService.java, v 0.1 2024‐07‐18 12:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope(value = "prototype")
public class BizSchedulerService {

    @Autowired
    private BizThreadSharedResource bizThreadSharedResource;

    @Autowired
    private BizSchedulerMinuteProcessor bizSchedulerMinuteProcessor;

    @Autowired
    private BizSyncMemberUnionProcessor bizSyncMemberUnionProcessor;

    @Autowired
    private BizGenerateAreaReportProcessor bizGenerateAreaReportProcessor;

    @Autowired
    private BizGenerateSubOrgReportProcessor bizGenerateSubOrgReportProcessor;

    @Autowired
    private BizGenerateTimeSeriesReportProcessor bizGenerateTimeSeriesReportProcessor;

    @Autowired
    private BizSubOrgDailyMonitorProcessor bizSubOrgDailyMonitorProcessor;

    @Autowired
    private BizCheckNoSubOrgProcessor bizCheckNoSubOrgProcessor;


    private BizGroupProcessor bizGroupProcessor;

    @Autowired
    private BizSampleOneProcessor bizSampleOneProcessor;

    @Autowired
    private BizSampleTwoProcessor bizSampleTwoProcessor;

    public BizResult execute(String scene) {

        final List<String> highPriorityProcess = new ArrayList<>();
        highPriorityProcess.add(BizProcessEvent.SYNC_MEMBER_UNION.getEventCode());
        highPriorityProcess.add(BizProcessEvent.GENERATE_REPORT_BY_AREA.getEventCode());
        highPriorityProcess.add(BizProcessEvent.GENERATE_REPORT_BY_SUB_ORG.getEventCode());
        highPriorityProcess.add(BizProcessEvent.GENERATE_REPORT_TIME_SERIES.getEventCode());

        return BizSchedulerTemplate.execute(scene, new BizSchedulerTemplate.Handler() {
            @Override
            public void preProcess(BizSchedulerScene schedulerScene) {
                AssertUtil.isNotTrue(schedulerScene == BizSchedulerScene.UNKNOWN, EzErrorCode.ILLEGAL_ACTION);

                boolean isOnProcess = bizThreadSharedResource.isOnProcess();
                String onProcessEvent = bizThreadSharedResource.getProcessEvent();
                boolean isProcessOverlap = isOnProcess && highPriorityProcess.contains(onProcessEvent);
                AssertUtil.isNotTrue(isProcessOverlap, EzErrorCode.SCHEDULER_PRIORITY_OVERLAP);
            }

            @Override
            public void process(BizSchedulerScene schedulerScene) {
                switch (schedulerScene) {
                    case MINUTE:
                        bizSchedulerMinuteProcessor.process();
                        break;

                    case RJL_DAILY_REPORT:
                        bizGroupProcessor = new BizGroupProcessor();
                        bizGroupProcessor.addProcessor(bizSyncMemberUnionProcessor);
                        bizGroupProcessor.addProcessor(bizGenerateAreaReportProcessor);
                        bizGroupProcessor.addProcessor(bizGenerateSubOrgReportProcessor);
                        bizGroupProcessor.addProcessor(bizGenerateTimeSeriesReportProcessor);
                        bizGroupProcessor.processInSequence("RJL0");
                        break;

                    case RJL_DAILY_SEND_SUB_ORG_REPORT:
                        bizSubOrgDailyMonitorProcessor.process("RJL0");
                        break;

                    case RJL_DAILY_CHECK_NO_SUB_ORG:
                        bizCheckNoSubOrgProcessor.process("RJL0");
                        break;

                    case SAMPLE_SEQUENCE:
                        bizGroupProcessor = new BizGroupProcessor();
                        bizGroupProcessor.addProcessor(bizSampleOneProcessor);
                        bizGroupProcessor.addProcessor(bizSampleTwoProcessor);
                        bizGroupProcessor.processInSequence(null);
                        break;

                    case SAMPLE_ASYNC:
                        bizGroupProcessor = new BizGroupProcessor();
                        bizGroupProcessor.addProcessor(bizSampleOneProcessor);
                        bizGroupProcessor.addProcessor(bizSampleTwoProcessor);
                        bizGroupProcessor.processAllAsync(null);
                        break;
                }
            }
        });
    }
}