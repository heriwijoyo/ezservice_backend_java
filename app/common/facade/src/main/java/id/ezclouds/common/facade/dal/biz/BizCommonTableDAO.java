/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.BizCommonTable;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonTableDAO.java, v 0.1 2024‐08‐16 1:31 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizCommonTableDAO {
    void store(BizCommonTable bizCommonTable);
    BizCommonTable getByCode(String orgId, String code);
}