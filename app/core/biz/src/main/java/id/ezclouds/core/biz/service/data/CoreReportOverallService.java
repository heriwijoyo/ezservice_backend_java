/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.data;

import id.ezclouds.common.facade.biz.report.BizReportOverallService;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportOverallService.java, v 0.1 2024‐09‐18 12:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportOverallService implements BizReportOverallService {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Override
    public List<BizReportOverall> getReportOverall(String orgId) {
        return bizReportOverallDAO.getAllReport(orgId);
    }

    @Override
    @Transactional
    public void updateReportOverall(String orgId, String reportKey, int value) {
        BizReportOverall reportOverall = bizReportOverallDAO
                .getAndLock(orgId, reportKey);
        bizReportOverallDAO.updateValue(reportOverall.getId(), value, DateUtil.getCurrentFormattedDateMillis());
    }
}