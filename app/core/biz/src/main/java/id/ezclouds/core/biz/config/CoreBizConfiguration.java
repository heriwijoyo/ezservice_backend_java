/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.config;

import id.ezclouds.common.facade.biz.data.BizSmartTableDataSource;
import id.ezclouds.common.model.biz.table.TableSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizConfiguration.java, v 0.1 2024‐09‐13 10:28 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Configuration
public class CoreBizConfiguration {

    @Autowired
    @Qualifier(value = "bizTableReport")
    private BizSmartTableDataSource reportTableDataSource;

    @Autowired
    @Qualifier(value = "bizReportRealCount")
    private BizSmartTableDataSource realCountTableDataSource;

    @Bean
    Map<TableSource, BizSmartTableDataSource> tableDataSourceMap() {
        Map<TableSource, BizSmartTableDataSource> sourceMap = new HashMap<>();
        sourceMap.put(TableSource.BIZ_TABLE_REPORT, reportTableDataSource);
        sourceMap.put(TableSource.BIZ_REPORT_REAL_COUNT, realCountTableDataSource);
        return sourceMap;
    }
}