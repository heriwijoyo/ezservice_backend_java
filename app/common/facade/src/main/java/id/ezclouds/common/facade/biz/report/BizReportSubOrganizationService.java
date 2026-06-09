/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.report;

import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportSubOrganizationService.java, v 0.1 2024‐10‐30 8:47 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportSubOrganizationService {

    BizResult getActiveSubOrganizations(String sessionId);
}