/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.biz.report.BizReportAccumulateCluster;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateClusterDAO.java, v 0.1 2024‐10‐21 9:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateClusterDAO {

    BizReportAccumulateCluster getAndLock(String orgId, String clusterId);

    void store(BizReportAccumulateCluster accumulateCluster);
}