/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.common.facade.dal.area.AreaDistrictDAO;
import id.ezclouds.common.facade.dal.report.BizReportByAreaDAO;
import id.ezclouds.common.facade.integration.BizObjectMapperService;
import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.report.BizReportByArea;
import id.ezclouds.common.util.StringUtil;
import id.ezclouds.core.process.biz.inner.BizInnerProcessCommonTable;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessReportAreaCommonTable.java, v 0.1 2024‐08‐25 10:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessReportAreaCommonTable extends BizAsyncProcessor {

    @Autowired
    private AreaDistrictDAO areaDistrictDAO;

    @Autowired
    private BizReportByAreaDAO bizReportByAreaDAO;

    @Autowired
    private BizInnerProcessCommonTable bizInnerProcessCommonTable;

    @Autowired
    private BizObjectMapperService bizObjectMapperService;

    @Override
    protected int maxProcessTime() {
        return 10 * 60 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String[] requestParam = ((String)request).split(",");
        String orgId = requestParam[0];
        String source = requestParam[1];
        String regencyId = requestParam[2];
        String pageId = requestParam[3];
        List<BizReportByArea> reportByAreas = bizReportByAreaDAO
                .getReportDistrictSource(orgId, source);
        List<CoreArea> districts = areaDistrictDAO.getByRegencyId(regencyId);

        for (CoreArea district : districts) {
            BizCommonTable commonTable = bizInnerProcessCommonTable
                    .getOrCreateCommonTable(orgId, district.getName());
            commonTable.setPageId(pageId);
            commonTable.setTitle(district.getName());
            commonTable.setData(getTableData(reportByAreas, district.getName()));
            commonTable.setConfig(null);

            bizInnerProcessCommonTable.updateCommonTable(commonTable);
        }
        return true;
    }

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.REPORT_AREA_COMMON_TABLE_PARSE;
    }

    private String getTableData(List<BizReportByArea> reportByAreas, String districtName) {
        List<List<String>> reportData = new ArrayList<>();

        for (BizReportByArea reportByArea : reportByAreas) {
            if (StringUtil.equals(districtName, reportByArea.getDistrictName())) {
                if (!"ALL".equals(reportByArea.getVillageName())) {

                    List<String> rowData = new ArrayList<>();
                    rowData.add(reportByArea.getVillageName());
                    rowData.add(""+ reportByArea.getVoterTotal());
                    rowData.add(""+ reportByArea.getVoterStrong());
                    rowData.add(""+ reportByArea.getVoterLazy());
                    rowData.add(""+ reportByArea.getGenderMale());
                    rowData.add(""+ reportByArea.getGenderFemale());
                    rowData.addAll(parseTpsData(reportByArea.getTpsData()));
                    reportData.add(rowData);
                }
            }
        }

        return bizObjectMapperService.toJson(reportData);
    }

    private List<String> parseTpsData(String tpsData) {
        List<String> data = new ArrayList<>();
        Map<String, String> tpsDataMap = bizObjectMapperService.jsonToMap(tpsData);
        for (int i = 1; i <= 6; i++) {
            String key = "0"+ i;
            String value = tpsDataMap.get(key);
            data.add(value != null ? value : "0");
        }

        return data;
    }
}