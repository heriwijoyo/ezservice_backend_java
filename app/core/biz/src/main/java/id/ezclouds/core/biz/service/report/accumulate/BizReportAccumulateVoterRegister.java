/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.facade.area.CoreAreaScanListener;
import id.ezclouds.common.facade.area.CoreAreaService;
import id.ezclouds.common.facade.area.CoreWorkingAreaService;
import id.ezclouds.common.facade.config.CoreConfigService;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.area.CoreAreaRecursive;
import id.ezclouds.common.model.biz.election.BizVoter;
import id.ezclouds.common.model.config.CoreOrgConfigType;
import id.ezclouds.common.model.process.ProcessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateVoterRegister.java, v 0.1 2024‐10‐02 3:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
@Qualifier("bizReportAccumulateVoterRegister")
public class BizReportAccumulateVoterRegister implements ReportAccumulateProcessor {

    @Autowired
    private TransactionTemplate transactionTemplate;

    private CoreConfigService coreConfigService;

    private CoreAreaService coreAreaService;

    private CoreWorkingAreaService coreWorkingAreaService;

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        BizVoter bizVoter = (BizVoter) payload;

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                coreWorkingAreaService.scanWorkingAreaRecursive(orgId, CoreAreaLevel.VILLAGE, new CoreAreaScanListener() {
                    @Override
                    public void areaOnTargetLevel(CoreAreaRecursive areaRecursive) {
                        processOnAreaLevel(areaRecursive, orgId, bizVoter);
                    }
                });

            }
        });

        handler.onFinished(ProcessStatus.EXCEPTION);
    }

    private void processOnAreaLevel(CoreAreaRecursive areaRecursive, String orgId, BizVoter bizVoter) {

    }
}