/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.biz.report.BizReportPage;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportPageDAO.java, v 0.1 2024‐10‐13 6:26 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportPageDAO {

    BizReportPage getReportPage(String section, String code);
}