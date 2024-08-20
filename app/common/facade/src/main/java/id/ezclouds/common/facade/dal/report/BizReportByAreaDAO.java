/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import id.ezclouds.common.model.report.BizReportByArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportByAreaDAO.java, v 0.1 2024‐07‐31 4:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportByAreaDAO {
    List<BizReportByArea> getReportDistrictAllSource(String orgId);
    List<BizReportByArea> getReportDistrictSource(String orgId, String source);
}