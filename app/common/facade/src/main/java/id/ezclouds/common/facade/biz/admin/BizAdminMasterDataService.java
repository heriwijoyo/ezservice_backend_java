/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminMasterDataService.java, v 0.1 2024‐09‐18 12:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminMasterDataService {

    BizResult getReportOverall(WebBizPageRequest request);

    BizResult reportOverallUpdate(WebBizUpdateRequest<BizReportOverall> request);
}