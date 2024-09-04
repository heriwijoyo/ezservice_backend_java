/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.data.BizMasterData;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMasterDataDAO.java, v 0.1 2024‐09‐04 8:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizMasterDataDAO {

    void storeOrUpdate(BizMasterData masterData);
}