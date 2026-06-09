/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.dal.report.BizReportRealCountDAO;
import id.ezclouds.common.model.biz.table.BizSmartTable;
import id.ezclouds.common.model.biz.table.BizSmartTableQueryScenario;
import id.ezclouds.common.model.biz.table.TableColumn;
import id.ezclouds.common.model.biz.table.TableConfig;
import id.ezclouds.common.model.constant.BizTableQueryConstant;
import id.ezclouds.common.model.report.BizReportRealCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportRealCountDataSource.java, v 0.1 2024‐09‐15 4:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
@Qualifier(value = "bizReportRealCount")
public class BizReportRealCountDataSource extends BaseDataSource {

    @Autowired
    private BizReportRealCountDAO bizReportRealCountDAO;

    @Override
    public BizSmartTable fetchColumnAndData(Map<String, String> conditions) {
        BizSmartTableQueryScenario queryScenario = getQueryScenario(conditions);

        BizSmartTable smartTable = new BizSmartTable();
        smartTable.setTitle(getTitle(conditions));
        smartTable.setConfig(new TableConfig());
        smartTable.setColumns(getColumns(conditions));
        smartTable.setRowData(getRowData(conditions));

        return smartTable;
    }

    private List<TableColumn> getColumns(Map<String, String> conditions) {
        BizSmartTableQueryScenario scenario = getQueryScenario(conditions);
        List<TableColumn> columns = new ArrayList<>();
        switch (scenario) {
            case REAL_COUNT_ALL:
            case REAL_COUNT_ALL_VERIFIED:
                columns.add(new TableColumn("Nama Kandidat", 120));
                columns.add(new TableColumn("Perolehan", 30));
                break;
        }
        return columns;
    }

    private List<List<String>> getRowData(Map<String, String> conditions) {
        BizSmartTableQueryScenario scenario = getQueryScenario(conditions);

        List<BizReportRealCount> realCounts = new ArrayList<>();
        switch (scenario) {
            case REAL_COUNT_ALL:
            case REAL_COUNT_ALL_VERIFIED:
                realCounts = bizReportRealCountDAO
                        .getByScene(
                                conditions.get(BizTableQueryConstant.ORG_ID),
                                conditions.get(BizTableQueryConstant.SCENARIO)
                        );
                break;

        }

        return parseToRowData(scenario, realCounts);
    }

    private List<List<String>> parseToRowData(BizSmartTableQueryScenario scenario, List<BizReportRealCount> realCounts) {
        List<List<String>> rowData = new ArrayList<>();
        switch (scenario) {
            case REAL_COUNT_ALL:
            case REAL_COUNT_ALL_VERIFIED:
                for (BizReportRealCount realCount : realCounts) {
                    List<String> row = new ArrayList<>();
                    row.add(realCount.getSceneId());
                    row.add(String.valueOf(realCount.getCountA()));
                    rowData.add(row);
                }
                break;
        }
        return rowData;
    }
}