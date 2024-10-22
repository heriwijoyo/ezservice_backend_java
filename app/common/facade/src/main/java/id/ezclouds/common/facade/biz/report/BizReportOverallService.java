/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.report;

import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallService.java, v 0.1 2024‐09‐04 5:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportOverallService {

    List<BizReportOverall> getReportOverall(String orgId);

    void updateReportOverall(String orgId, BizReportOverallKey overallKey, int value);
}