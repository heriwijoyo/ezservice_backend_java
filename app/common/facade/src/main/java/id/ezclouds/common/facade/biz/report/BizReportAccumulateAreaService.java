/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.biz.report;

import id.ezclouds.common.model.area.CoreArea;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.model.biz.report.BizReportArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateAreaService.java, v 0.1 2024‐10‐13 11:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateAreaService {

    List<BizReportAccumulateArea> getAccumulateAreaByParentId(String orgId, CoreAreaLevel areaLevel, String parentId);


    /**
     * temp quick solution for RJL report
     */

    BizReportArea getReportArea(String orgId);
    BizReportArea getReportArea(String orgId, CoreArea coreArea);
    BizReportArea getReportAreaPollStation(String orgId, String villageId);
}