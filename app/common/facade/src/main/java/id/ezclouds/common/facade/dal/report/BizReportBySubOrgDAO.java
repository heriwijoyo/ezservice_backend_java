/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.report;

import id.ezclouds.common.model.report.BizReportBySubOrg;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportBySubOrgDAO.java, v 0.1 2024‐08‐01 1:38 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportBySubOrgDAO {
    List<BizReportBySubOrg> getReportAllSource(String orgId);
}