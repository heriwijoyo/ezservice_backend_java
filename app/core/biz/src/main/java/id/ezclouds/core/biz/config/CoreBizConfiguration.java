/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.config;

import id.ezclouds.common.facade.biz.data.BizSmartTableDataSource;
import id.ezclouds.common.model.biz.table.TableSource;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.core.biz.service.report.accumulate.ReportAccumulateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizConfiguration.java, v 0.1 2024‐09‐13 10:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Configuration
public class CoreBizConfiguration {

    @Autowired
    @Qualifier(value = "bizTableReport")
    private BizSmartTableDataSource reportTableDataSource;

    @Autowired
    @Qualifier(value = "bizReportRealCount")
    private BizSmartTableDataSource realCountTableDataSource;

    @Autowired
    @Qualifier(value = "bizReportAccumulateVoterRegister")
    private ReportAccumulateProcessor bizReportAccumulateVoterRegister;

    @Autowired
    @Qualifier(value = "bizReportAccumulateVoterInvalidRegister")
    private ReportAccumulateProcessor bizReportAccumulateVoterInvalidRegister;

    @Autowired
    @Qualifier(value = "bizReportAccumulateClusterRegister")
    private ReportAccumulateProcessor bizReportAccumulateClusterRegister;

    @Autowired
    @Qualifier(value = "bizReportAccumulateMemberRegister")
    private ReportAccumulateProcessor bizReportAccumulateMemberRegister;

    @Bean
    public Map<TableSource, BizSmartTableDataSource> tableDataSourceMap() {
        Map<TableSource, BizSmartTableDataSource> sourceMap = new HashMap<>();
        sourceMap.put(TableSource.BIZ_TABLE_REPORT, reportTableDataSource);
        sourceMap.put(TableSource.BIZ_REPORT_REAL_COUNT, realCountTableDataSource);
        return sourceMap;
    }

    @Bean
    public Map<EzCoreTopic, ReportAccumulateProcessor> reportAccumulateProcessorMap() {
        Map<EzCoreTopic, ReportAccumulateProcessor> processorMap = new HashMap<>();
        processorMap.put(EzCoreTopic.ELECTION_VOTER_REGISTER, bizReportAccumulateVoterRegister);
        processorMap.put(EzCoreTopic.ELECTION_VOTER_REGISTER_INVALID, bizReportAccumulateVoterInvalidRegister);
        processorMap.put(EzCoreTopic.CORE_SUB_ORG_CREATE, bizReportAccumulateClusterRegister);
        processorMap.put(EzCoreTopic.CORE_MEMBER_REGISTER, bizReportAccumulateMemberRegister);
        return processorMap;
    }
}