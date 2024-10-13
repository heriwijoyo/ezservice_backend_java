/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.facade.biz.report.BizReportOverallService;
import id.ezclouds.common.facade.broker.CoreEventPublisherService;
import id.ezclouds.common.facade.dal.biz.report.BizAccumulateAreaExtDAO;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateProcessDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.biz.report.BizReportAccumulateProcess;
import id.ezclouds.common.model.biz.report.RecoverBizVoterAccumulateArea;
import id.ezclouds.common.model.broker.event.EzCommonEvent;
import id.ezclouds.common.model.broker.event.OverallReportChangeEvent;
import id.ezclouds.common.model.broker.topic.EzCoreTopic;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.exception.ExceptionUtil;
import id.ezclouds.common.util.logger.CommonLoggerConstant;
import id.ezclouds.common.util.logger.LogUtil;
import id.ezclouds.core.biz.service.report.accumulate.ReportAccumulateProcessHandler;
import id.ezclouds.core.biz.service.report.accumulate.ReportAccumulateProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggerConstant.ASYNC_PROCESS);

    private static List<EzCoreTopic> allowedTopics = Arrays.asList(
            EzCoreTopic.ELECTION_CANVASS_RECORD_ADD,
            EzCoreTopic.ELECTION_VOTER_REGISTER,
            EzCoreTopic.ELECTION_VOTER_REGISTER_INVALID,
            EzCoreTopic.ELECTION_QUICK_COUNT_SUBMIT,
            EzCoreTopic.ELECTION_QUICK_COUNT_VERIFY,
            EzCoreTopic.BIZ_REPORT_RECOVER_ACCUMULATE_VOTER
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
    private TransactionTemplate reportOverallUpdateTemplate;

    @Autowired
    private Map<EzCoreTopic, ReportAccumulateProcessor> reportAccumulateProcessorMap;

    @Autowired
    private BizAccumulateAreaExtDAO bizAccumulateAreaExtDAO;

    @Autowired
    private BizReportOverallService bizReportOverallService;

    @Autowired
    private CoreEventPublisherService coreEventPublisherService;

    @Async
    @EventListener
    public void handleEventForReport(EzCommonEvent ezCommonEvent) {
        boolean isOnFilter = allowedTopics.contains(ezCommonEvent.getCoreTopic());
        if (!isOnFilter) {
            return;
        }

        String orgId = ezCommonEvent.getOrgId();
        EzCoreTopic ezCoreTopic = ezCommonEvent.getCoreTopic();
        Object eventPayload = ezCommonEvent.getPayload();

        String processId;
        if (ezCommonEvent.getCoreTopic() == EzCoreTopic.BIZ_REPORT_RECOVER_ACCUMULATE_VOTER) {
            RecoverBizVoterAccumulateArea recoverData = (RecoverBizVoterAccumulateArea) ezCommonEvent.getPayload();
            processId = recoverData.getProcessId();
            ezCoreTopic = recoverData.getEzCoreTopic();
            eventPayload = ezCoreTopic == EzCoreTopic.ELECTION_VOTER_REGISTER ? recoverData.getBizVoter() : recoverData.getBizVoterInvalid();
        } else {
            String currentTime = DateUtil.getCurrentFormattedDateMillis();
            processId = HashUtil.createHash(orgId, ezCoreTopic.getCode(), currentTime);

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
                LogUtil.info(LOGGER, "REPORT_ACCUMULATE_PROCESS_FAILED,ORG_ID=", orgId, ",TOPIC=", ezCoreTopic.getCode(), ",exception:", ExceptionUtil.getStackTrace(e));
                return;
            }
        }

        reportAccumulateProcessorMap
                .get(ezCoreTopic)
                .process(orgId, eventPayload, new ReportAccumulateProcessHandler() {
                    @Override
                    public void onFinished(ProcessStatus status, String exceptionStack) {
                        finishProcess(orgId, processId, status, exceptionStack);
                    }
                });
    }

    private void finishProcess(String orgId, String processId, ProcessStatus processStatus, String exceptionStack) {
        finishProcessTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                BizReportAccumulateProcess accumulateProcess = bizReportAccumulateProcessDAO
                        .getAndLock(processId);

                accumulateProcess.setStatus(processStatus);
                accumulateProcess.setFinishedTime(DateUtil.getCurrentFormattedDateMillis());
                accumulateProcess.setExceptionStack(exceptionStack);
                bizReportAccumulateProcessDAO.store(accumulateProcess);

            }
        });

        reportOverallUpdateTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                int pollStationCount = bizAccumulateAreaExtDAO.getCountPollStation(orgId);

                bizReportOverallService.updateReportOverall(
                        orgId,
                        BizReportOverallKey.VOTER_BASE_VOTE_STATION_COUNT.getCode(),
                        pollStationCount
                );
            }
        });

        coreEventPublisherService.publish(new OverallReportChangeEvent(orgId));
    }
}