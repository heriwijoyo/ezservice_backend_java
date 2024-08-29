/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.request.admin.CommonTableCreateRequest;
import id.ezclouds.common.model.request.admin.WebAdminRequest;
import id.ezclouds.common.model.request.admin.WebBizDetailRequest;
import id.ezclouds.common.model.request.admin.WebBizUpdateRequest;
import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminConfigService.java, v 0.1 2024‐08‐16 12:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminConfigService {

    BizResult getBizCommonTables(WebBizPageRequest request);

    BizResult createBizCommonTable(CommonTableCreateRequest request);

    BizResult updateBizCommonTable(WebBizUpdateRequest<BizCommonTable> request);

    BizResult getBizCommonTable(WebBizDetailRequest<String> request);

    BizResult getWatzapNumberKey(WebAdminRequest request);

    BizResult updateWatzapNumberKey(WebBizUpdateRequest<String> request);
}