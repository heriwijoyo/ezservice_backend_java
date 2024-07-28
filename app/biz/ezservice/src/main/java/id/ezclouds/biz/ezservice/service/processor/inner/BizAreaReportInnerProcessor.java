/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor.inner;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportByAreaDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizReportByAreaRepository;
import id.ezclouds.common.facade.dal.report.BizReportOverallDAO;
import id.ezclouds.common.model.report.BizReportOverall;
import id.ezclouds.common.model.report.BizReportOverallKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAreaReportInnerProcessor.java, v 0.1 2024‐07‐24 4:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAreaReportInnerProcessor {

    @Autowired
    private BizReportByAreaRepository bizReportByAreaRepository;

    @Autowired
    private BizReportOverallDAO bizReportOverallDAO;

    @Transactional
    public long deleteAllReport(String orgId) {
        return bizReportByAreaRepository.deleteByOrgId(orgId);
    }

    @Transactional
    public void storeBizReport(BizReportByAreaDO reportByAreaDO) {
        bizReportByAreaRepository.saveAndFlush(reportByAreaDO);
    }

    @Transactional
    public void storeTpsCoverage(String orgId, int totalTps) {
        BizReportOverall reportOverall = new BizReportOverall();
        reportOverall.setOrgId(orgId);
        reportOverall.setKeyId(BizReportOverallKey.TOTAL_TPS.getCode());
        reportOverall.setCount(totalTps);
        bizReportOverallDAO.reStore(reportOverall);
    }
}