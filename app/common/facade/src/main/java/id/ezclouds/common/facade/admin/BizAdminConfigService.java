/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.admin;

import id.ezclouds.common.model.request.admin.CreateCommonTableRequest;
import id.ezclouds.common.model.result.BaseResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminConfigService.java, v 0.1 2024‐08‐16 12:45 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminConfigService {
    BaseResult createBizCommonTable(CreateCommonTableRequest request);
}