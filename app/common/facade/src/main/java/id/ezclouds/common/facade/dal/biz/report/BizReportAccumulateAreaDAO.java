/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz.report;

import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import id.ezclouds.common.model.biz.report.BizReportArea;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateAreaDAO.java, v 0.1 2024‐10‐03 1:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizReportAccumulateAreaDAO {

    void store(BizReportAccumulateArea accumulateArea);

    BizReportAccumulateArea getPollStationReportArea(String orgId, String villageId);

    BizReportAccumulateArea getAndLock(String orgId, CoreAreaLevel areaLevel, String areaLevelId);

    List<BizReportAccumulateArea> getByParentId(String orgId, CoreAreaLevel areaLevel, String areaParentId);
}