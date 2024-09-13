/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.table;

import id.ezclouds.common.facade.biz.BizSmartTableService;
import id.ezclouds.common.facade.biz.data.BizSmartTableDataSource;
import id.ezclouds.common.model.biz.table.BizSmartTable;
import id.ezclouds.common.model.biz.table.TableSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSmartTableService.java, v 0.1 2024‐09‐13 10:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreSmartTableService implements BizSmartTableService {

    @Autowired
    Map<TableSource, BizSmartTableDataSource> tableDataSourceMap;

    @Override
    public BizSmartTable fetchColumnAndData(TableSource tableSource, Map<String, String> conditions) {

        return tableDataSourceMap
                .get(tableSource)
                .fetchColumnAndData(conditions);
    }
}