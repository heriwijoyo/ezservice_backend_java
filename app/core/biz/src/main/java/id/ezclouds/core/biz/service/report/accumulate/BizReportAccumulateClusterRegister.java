/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report.accumulate;

import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateClusterDAO;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.core.organization.SubOrganization;
import id.ezclouds.common.model.process.ProcessStatus;
import id.ezclouds.common.model.report.BizReportOverallKey;
import id.ezclouds.common.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateClusterRegister.java, v 0.1 2024‐10‐23 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
@Scope("prototype")
@Qualifier("bizReportAccumulateClusterRegister")
public class BizReportAccumulateClusterRegister implements ReportAccumulateProcessor {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Autowired
    private BizReportAccumulateClusterDAO bizReportAccumulateClusterDAO;

    @Override
    public void process(String orgId, Object payload, ReportAccumulateProcessHandler handler) {
        SubOrganization subOrganization = (SubOrganization) payload;
        subOrganization.setOrgId(orgId);

        try {

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    processAccumulate(subOrganization);
                }
            });
            handler.onFinished(ProcessStatus.SUCCESS, null);

        } catch (Exception e) {
            handler.onFinished(ProcessStatus.EXCEPTION, ExceptionUtil.getStackTrace(e));
        }
    }

    private void processAccumulate(SubOrganization subOrganization) {

        bizReportOverallDAO.getAndLock(subOrganization.getOrgId(), BizReportOverallKey.VOTER_BASE_CLUSTER_COUNT);
    }
}