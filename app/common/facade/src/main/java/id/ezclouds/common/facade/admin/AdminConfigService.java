/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.admin;

import id.ezclouds.common.model.biz.BizCommonTable;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminConfigService.java, v 0.1 2024‐08‐17 8:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AdminConfigService {
    void createBizCommonTable(BizCommonTable bizCommonTable);
}