/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process;

import id.ezclouds.common.model.process.ProcessName;
import id.ezclouds.core.process.biz.*;
import id.ezclouds.core.process.debug.BizProcessDebugger;
import id.ezclouds.core.process.init.BizProcessInitReportAccumulateArea;
import id.ezclouds.core.process.migration.CoreProcessMigrateMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreProcessConfiguration.java, v 0.1 2024‐10‐08 4:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Configuration
public class CoreProcessConfiguration {

    @Autowired
    private BizProcessorSurveyResponseParse bizProcessorSurveyResponseParse;

    @Autowired
    private BizProcessReportAreaCommonTable bizProcessReportAreaCommonTable;

    @Autowired
    private BizProcessInitMasterDataArea bizProcessInitMasterDataArea;

    @Autowired
    private BizProcessInitReportOverall bizProcessInitReportOverall;

    @Autowired
    private BizProcessInitReportRealCountOverall bizProcessInitReportRealCountOverall;

    @Autowired
    private BizProcessInitReportRealCountArea bizProcessInitReportRealCountArea;

    @Autowired
    private CoreProcessMigrateMember coreProcessMigrateMember;

    @Autowired
    private BizProcessDebugger bizProcessDebugger;

    @Autowired
    private BizProcessInitReportAccumulateArea bizProcessInitReportAccumulateArea;

    @Bean
    public Map<ProcessName, BizAsyncProcessor> bizAsyncProcessorMap() {
        Map<ProcessName, BizAsyncProcessor> processorMap = new HashMap<>();
        processorMap.put(ProcessName.INIT_MASTER_DATA_AREA, bizProcessInitMasterDataArea);
        processorMap.put(ProcessName.INIT_REPORT_ACCUMULATE_AREA, bizProcessInitReportAccumulateArea);
        processorMap.put(ProcessName.INIT_REPORT_OVERALL, bizProcessInitReportOverall);

        processorMap.put(ProcessName.INIT_MIGRATE_MEMBER, coreProcessMigrateMember);

        return processorMap;
    }
}