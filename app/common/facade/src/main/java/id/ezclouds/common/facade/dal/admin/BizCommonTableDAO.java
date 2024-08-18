/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.admin;

import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.PageResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonTableDAO.java, v 0.1 2024‐08‐16 1:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizCommonTableDAO {

    PageResult<BizCommonTable> getCommonTables(WebBizPageRequest request);

    void store(BizCommonTable bizCommonTable);

    void update(BizCommonTable bizCommonTable);

    BizCommonTable getByTableId(String tableId);

    BizCommonTable getByCode(String orgId, String code);
}