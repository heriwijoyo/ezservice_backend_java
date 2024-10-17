/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz;

import id.ezclouds.common.model.biz.table.BizSmartTable;
import id.ezclouds.common.model.biz.table.TableSource;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSmartTableService.java, v 0.1 2024‐09‐11 1:19 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizSmartTableService {

    BizSmartTable fetchColumnAndData(TableSource tableSource, Map<String, String> conditions);
}