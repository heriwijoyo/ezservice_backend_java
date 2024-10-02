/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.biz.report.BizAccumulateAreaExt;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAccumulateAreaExtDAO.java, v 0.1 2024‐10‐03 2:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizAccumulateAreaExtDAO {

    void store(BizAccumulateAreaExt accumulateAreaExt);

    BizAccumulateAreaExt getAndLock(String accumulateAreaId, String orgId, String accumulateKey, String accumulateVariable);
}