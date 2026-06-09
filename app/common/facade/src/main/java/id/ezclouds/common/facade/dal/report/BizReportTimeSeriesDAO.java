/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import id.ezclouds.common.model.report.BizReportTimeSeries;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportTimeSeriesDAO.java, v 0.1 2024‐07‐31 2:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportTimeSeriesDAO {
    List<BizReportTimeSeries> getReports(String orgId, String reportId, List<String> timeFrames);
}