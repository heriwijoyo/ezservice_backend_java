/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.report;

import id.ezclouds.common.facade.dal.biz.election.BizVoterDAO;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.core.process.biz.BizAsyncProcessor;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessReportDailyReset.java, v 0.1 2024‐10‐26 3:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessReportDailyReset extends BizAsyncProcessor {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Autowired
    private BizVoterDAO bizVoterDAO;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.REPORT_DAILY_RESET;
    }

    @Override
    protected int maxProcessTime() {
        return 10 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String param = (String) request;
        logData.add("ORG_IDs="+ param);

        List<BizReportOverallKey> overallKeys = Arrays.asList(
                BizReportOverallKey.VOTER_BASE_VOTER_COUNT_TODAY,
                BizReportOverallKey.VOTER_BASE_CLUSTER_COUNT_TODAY
        );

        for (String orgId : param.split(",")) {
            for (BizReportOverallKey overallKey : overallKeys) {

                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        resetOverallData(orgId, overallKey);
                    }
                });

            }

            updateOverallYesterday(orgId);
        }
        return true;
    }

    private void resetOverallData(String orgId, BizReportOverallKey overallKey) {
        BizReportOverall reportOverall = bizReportOverallDAO
                .getAndLock(orgId, overallKey);
        if (reportOverall == null) {
            reportOverall = new BizReportOverall();
            reportOverall.setId(HashUtil.createHash(orgId, overallKey.getCode()));
            reportOverall.setOrgId(orgId);
            reportOverall.setKeyId(overallKey.getCode());
        }
        reportOverall.setCount(0);
        reportOverall.setUpdatedTime(DateUtil.getCurrentFormattedDateMillis());

        bizReportOverallDAO.store(reportOverall);
    }

    private void updateOverallYesterday(String orgId) {
        Date yesterday = DateUtil.getDateAfterDays(new Date(), -1);
        String startDate = DateUtil.getFormattedDayStart(yesterday);
        String endDate = DateUtil.getFormattedDayEnd(yesterday);
        int yesterdayVoterCount = bizVoterDAO.countWithinDate(orgId, startDate, endDate);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                BizReportOverallKey overallKey = BizReportOverallKey.VOTER_BASE_VOTER_COUNT_YESTERDAY;
                BizReportOverall reportOverall = bizReportOverallDAO
                        .getAndLock(orgId, overallKey);
                if (reportOverall == null) {
                    reportOverall = new BizReportOverall();
                    reportOverall.setId(HashUtil.createHash(orgId, overallKey.getCode()));
                    reportOverall.setOrgId(orgId);
                    reportOverall.setKeyId(overallKey.getCode());
                }
                reportOverall.setCount(yesterdayVoterCount);
                reportOverall.setUpdatedTime(DateUtil.getCurrentFormattedDateMillis());

                bizReportOverallDAO.store(reportOverall);
            }
        });
    }
}