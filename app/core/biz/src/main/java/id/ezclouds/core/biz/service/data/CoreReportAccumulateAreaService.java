/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.report.BizReportAccumulateAreaService;
import id.ezclouds.common.facade.dal.biz.report.BizReportAccumulateAreaDAO;
import id.ezclouds.common.model.area.CoreAreaLevel;
import id.ezclouds.common.model.biz.report.BizReportAccumulateArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportAccumulateAreaService.java, v 0.1 2024‐10‐14 12:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportAccumulateAreaService implements BizReportAccumulateAreaService {

    @Autowired
    private BizReportAccumulateAreaDAO bizReportAccumulateAreaDAO;

    @Override
    public List<BizReportAccumulateArea> getAccumulateAreaByParentId(String orgId, CoreAreaLevel areaLevel, String parentId) {
        return bizReportAccumulateAreaDAO.getByParentId(orgId, areaLevel, parentId);
    }
}