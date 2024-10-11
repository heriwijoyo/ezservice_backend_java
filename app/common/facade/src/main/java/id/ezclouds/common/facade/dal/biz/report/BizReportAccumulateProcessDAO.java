/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.biz.report.BizReportAccumulateProcess;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateProcessDAO.java, v 0.1 2024‐10‐02 2:42 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateProcessDAO {

    void store(BizReportAccumulateProcess accumulateProcess);

    BizReportAccumulateProcess getAndLock(String processId);

    List<BizReportAccumulateProcess> getFailedProcess(String orgId, int size);
}