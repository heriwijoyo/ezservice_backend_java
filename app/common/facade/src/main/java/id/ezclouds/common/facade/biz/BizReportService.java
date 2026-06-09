/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz;

import id.ezclouds.common.model.report.BizMainReport;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportService.java, v 0.1 2024‐07‐29 6:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportService {
    BizMainReport getMainReport(String orgId);
}