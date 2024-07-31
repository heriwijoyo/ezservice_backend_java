/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportByAreaDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.report.BizReportByArea;
import id.ezclouds.core.dal.report.converter.BizReportConverter;
import id.ezclouds.core.dal.report.repo.CoreReportByAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportByAreaDAO.java, v 0.1 2024‐07‐31 4:57 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportByAreaDAO implements BizReportByAreaDAO {

    @Autowired
    private CoreReportByAreaRepository coreReportByAreaRepository;

    @EzDAOLogger
    @Override
    public List<BizReportByArea> getReportDistrictAllSource(String orgId) {
        return coreReportByAreaRepository
                .fetchDistrictAllSource(orgId)
                .stream()
                .map(BizReportConverter::convert)
                .collect(Collectors.toList());
    }

    @EzDAOLogger
    @Override
    public List<BizReportByArea> getReportDistrictSource(String orgId, String source) {
        return coreReportByAreaRepository
                .fetchDistrictSource(orgId, source)
                .stream()
                .map(BizReportConverter::convert)
                .collect(Collectors.toList());
    }
}