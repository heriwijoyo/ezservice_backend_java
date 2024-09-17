/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessReportOverall.java, v 0.1 2024‐07‐28 7:53 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessReportOverall {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Transactional
    public void storeReport(String orgId, String keyId, int count) {
        BizReportOverall reportOverall = bizReportOverallDAO.getAndLock(orgId, keyId);
        bizReportOverallDAO.updateValue(reportOverall.getId(), count, DateUtil.getCurrentFormattedDateMillis());
    }

    public void init(String orgId, String keyId) {
        bizReportOverallDAO.create(orgId, keyId);
    }
}