/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.admin;

import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.PageResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminConfigService.java, v 0.1 2024‐08‐17 8:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AdminConfigService {
    PageResult<BizCommonTable> getBizCommonTables(WebBizPageRequest request);
    void createBizCommonTable(BizCommonTable bizCommonTable);
    BizCommonTable getBizCommonTable(String tableId);
}