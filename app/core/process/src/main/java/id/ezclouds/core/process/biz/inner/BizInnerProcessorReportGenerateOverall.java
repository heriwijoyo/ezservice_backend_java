/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz.inner;

import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizInnerProcessorReportGenerateOverall.java, v 0.1 2024‐07‐28 7:53 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizInnerProcessorReportGenerateOverall {

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Transactional
    public long deleteAllReport(String orgId) {
        return bizReportOverallDAO.deleteAll(orgId);
    }

    @Transactional
    public void storeReport(String orgId, String keyId, int count) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        BizReportOverall reportOverall = new BizReportOverall();
        reportOverall.setId(HashUtil.createHash(orgId, keyId, currentTime));
        reportOverall.setOrgId(orgId);
        reportOverall.setKeyId(keyId);
        reportOverall.setCount(count);
        reportOverall.setCreatedTime(currentTime);
        bizReportOverallDAO.store(reportOverall);
    }
}