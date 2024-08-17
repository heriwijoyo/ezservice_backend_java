/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.request.admin.CommonTableCreateRequest;
import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminConfigService.java, v 0.1 2024‐08‐16 12:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminConfigService {
    BizResult createBizCommonTable(CommonTableCreateRequest request);
    BizResult getByCode(String orgId, String code);
}