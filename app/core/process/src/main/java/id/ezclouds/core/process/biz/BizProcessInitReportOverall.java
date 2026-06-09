/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessInitReportOverall.java, v 0.1 2024‐09‐17 3:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessInitReportOverall extends BizAsyncProcessor {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

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

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                for (BizReportOverallKey overallKey : BizReportOverallKey.values()) {
                    bizReportOverallDAO.create(orgId, overallKey.getCode());
                }

            }
        });

        return true;
    }
}