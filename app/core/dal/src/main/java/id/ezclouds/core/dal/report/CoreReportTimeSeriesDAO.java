/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportTimeSeriesDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.report.BizReportTimeSeries;
import id.ezclouds.core.dal.report.converter.BizReportOverallConverter;
import id.ezclouds.core.dal.report.repo.CoreReportTimeSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportTimeSeriesDAO.java, v 0.1 2024‐07‐31 2:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportTimeSeriesDAO implements BizReportTimeSeriesDAO {

    @Autowired
    private CoreReportTimeSeriesRepository coreReportTimeSeriesRepository;

    @EzDAOLogger
    @Override
    public List<BizReportTimeSeries> getReports(String orgId, String reportId, List<String> timeFrames) {
        return coreReportTimeSeriesRepository
                .findByOrgIdAndAndReportIdAndTimeFrameIn(orgId, reportId, timeFrames)
                .stream()
                .map(BizReportOverallConverter::convert)
                .collect(Collectors.toList());
    }
}