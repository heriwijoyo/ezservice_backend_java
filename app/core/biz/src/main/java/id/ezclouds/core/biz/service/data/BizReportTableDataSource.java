/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.data.BizSmartTableDataSource;
import id.ezclouds.common.model.biz.table.BizSmartTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportTableDataSource.java, v 0.1 2024‐09‐13 10:23 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
@Qualifier(value = "bizTableReport")
public class BizReportTableDataSource implements BizSmartTableDataSource {

    @Override
    public BizSmartTable fetchColumnAndData(Map<String, String> conditions) {
        return null;
    }
}