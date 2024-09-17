/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.data;

import id.ezclouds.common.model.report.BizReportOverall;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataService.java, v 0.1 2024‐09‐04 5:59 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizMasterDataService {

    List<BizReportOverall> getReportOverall(String orgId);
}