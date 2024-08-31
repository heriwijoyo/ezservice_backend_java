/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz;

import id.ezclouds.common.model.result.BizResult;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizUniqueService.java, v 0.1 2024‐08‐31 2:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizUniqueService {

    BizResult checkUnique(String orgId, String scenario, String param);

}