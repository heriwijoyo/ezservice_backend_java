/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.admin;

import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAdminCoreAreaService.java, v 0.1 2024‐09‐20 12:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAdminCoreAreaService {

    BizResult getWorkingAreaDistricts(String sessionId);
}