/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.data;

import id.ezclouds.common.model.biz.table.BizSmartTable;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSmartTableDataSource.java, v 0.1 2024‐09‐13 10:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizSmartTableDataSource {

    BizSmartTable fetchColumnAndData(Map<String, String> conditions);
}