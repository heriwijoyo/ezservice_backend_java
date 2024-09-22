/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.admin;

import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminCommonService.java, v 0.1 2024‐09‐22 11:25 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AdminCommonService {

    BizResult getAdminAppData(String sessionId);
}