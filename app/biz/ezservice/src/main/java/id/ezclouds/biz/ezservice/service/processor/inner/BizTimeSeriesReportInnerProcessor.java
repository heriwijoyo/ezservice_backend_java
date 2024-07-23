/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.processor.inner;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportTimeSeriesDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizReportTimeSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizTimeSeriesReportInnerProcessor.java, v 0.1 2024‐07‐24 5:10 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizTimeSeriesReportInnerProcessor {

    @Autowired
    private BizReportTimeSeriesRepository bizReportTimeSeriesRepository;

    @Transactional
    public long deleteAllReport(String orgId) {
        return bizReportTimeSeriesRepository.deleteByOrgId(orgId);
    }

    @Transactional
    public void storeReport(BizReportTimeSeriesDO timeSeriesDO) {
        bizReportTimeSeriesRepository.saveAndFlush(timeSeriesDO);
    }
}