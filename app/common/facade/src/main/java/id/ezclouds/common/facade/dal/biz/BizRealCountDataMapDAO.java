/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.data.BizRealCountDataMap;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizRealCountDataMapDAO.java, v 0.1 2024‐09‐16 11:44 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizRealCountDataMapDAO {

    List<BizRealCountDataMap> getDataMap(String orgId, String scene);
}