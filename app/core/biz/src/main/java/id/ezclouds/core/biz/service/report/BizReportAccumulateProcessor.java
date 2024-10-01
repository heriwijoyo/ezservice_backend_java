/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateProcessDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.report.BizReportAccumulateProcess;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.biz.service.report.accumulate.ReportAccumulateProcessHandler;
import id.ezclouds.core.biz.service.report.accumulate.ReportAccumulateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateProcessor.java, v 0.1 2024‐10‐02 1:29 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizReportAccumulateProcessor {

    private static List<EzCoreTopic> allowedTopics = Arrays.asList(
            EzCoreTopic.ELECTION_CANVASS_RECORD_ADD,
            EzCoreTopic.ELECTION_VOTER_REGISTER,
            EzCoreTopic.ELECTION_QUICK_COUNT_SUBMIT,
            EzCoreTopic.ELECTION_QUICK_COUNT_VERIFY
    );

    @Autowired
    private BizReportAccumulateProcessDAO bizReportAccumulateProcessDAO;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Autowired
    private TransactionTemplate initProcessTemplate;

    @Autowired
    private TransactionTemplate finishProcessTemplate;

    @Autowired
    private Map<EzCoreTopic, ReportAccumulateProcessor> reportAccumulateProcessorMap;

    @Async
    @EventListener
    public void handleEventForReport(EzCommonEvent ezCommonEvent) {
        boolean isOnFilter = allowedTopics.contains(ezCommonEvent.getCoreTopic());
        if (!isOnFilter) {
            return;
        }

        String orgId = ezCommonEvent.getOrgId();
        String topic = ezCommonEvent.getCoreTopic().getCode();
        String currentTime = DateUtil.getCurrentFormattedDateMillis();
        String processId = HashUtil.createHash(orgId, topic, currentTime);

        try {
            initProcessTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    BizReportAccumulateProcess accumulateProcess = new BizReportAccumulateProcess();
                    accumulateProcess.setProcessId(processId);
                    accumulateProcess.setOrgId(orgId);
                    accumulateProcess.setTopic(ezCommonEvent.getCoreTopic());
                    accumulateProcess.setPayload(bizObjectMapperService.toJson(ezCommonEvent.getPayload()));
                    accumulateProcess.setStatus(ProcessStatus.INIT);
                    accumulateProcess.setCreatedTime(currentTime);

                    bizReportAccumulateProcessDAO.store(accumulateProcess);
                }
            });
        } catch (Exception e) {
            //TODO: add logger if the init process failed
            return;
        }

        reportAccumulateProcessorMap
                .get(ezCommonEvent.getCoreTopic())
                .process(orgId, ezCommonEvent.getPayload(), new ReportAccumulateProcessHandler() {
                    @Override
                    public void onFinished(ProcessStatus status) {
                        finishProcess(processId, status);
                    }
                });
    }

    private void finishProcess(String processId, ProcessStatus processStatus) {
        finishProcessTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                BizReportAccumulateProcess accumulateProcess = bizReportAccumulateProcessDAO
                        .getAndLock(processId);

                accumulateProcess.setStatus(processStatus);
                accumulateProcess.setFinishedTime(DateUtil.getCurrentFormattedDateMillis());
                bizReportAccumulateProcessDAO.store(accumulateProcess);

            }
        });
    }
}