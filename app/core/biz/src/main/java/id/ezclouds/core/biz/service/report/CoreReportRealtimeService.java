/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.report;

import id.ezclouds.common.facade.biz.BizReportRealtimeService;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportRealtimeService.java, v 0.1 2024‐09‐09 10:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreReportRealtimeService implements BizReportRealtimeService {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Override
    @Transactional
    public void accumulateValue(String orgId, String reportKey, int value) {
        BizReportOverall report = bizReportOverallDAO.getAndLock(orgId, reportKey);
        int newValue = report.getCount() + value;
        bizReportOverallDAO.updateValue(report.getId(), newValue, DateUtil.getCurrentFormattedDateMillis());
    }
}