/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.realcount.BizRealCountContract;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRealCountContractDAO.java, v 0.1 2024‐09‐19 1:22 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizRealCountContractDAO {

    BizRealCountContract getByCode(String orgId, String code);
}