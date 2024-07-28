/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import id.ezclouds.common.model.report.BizReportOverall;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallDAO.java, v 0.1 2024‐07‐28 7:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportOverallDAO {

    void store(BizReportOverall bizReportOverall);
    long deleteAll(String orgId);
}