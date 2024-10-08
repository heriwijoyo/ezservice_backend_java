/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import id.ezclouds.common.model.report.BizReportOverall;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallDAO.java, v 0.1 2024‐07‐28 7:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportOverallDAO {

    void create(String orgId, String keyId);

    List<BizReportOverall> getAllReport(String orgId);
    BizReportOverall getAndLock(String orgId, String reportKey);
    void store(BizReportOverall reportOverall);

    void updateValue(String reportId, int count, String updatedTime);
}