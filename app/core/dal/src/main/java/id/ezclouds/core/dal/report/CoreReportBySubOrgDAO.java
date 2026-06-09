/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportBySubOrgDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.report.BizReportBySubOrg;
import id.ezclouds.core.dal.report.converter.BizReportConverter;
import id.ezclouds.core.dal.report.repo.CoreReportBySubOrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportBySubOrgDAO.java, v 0.1 2024‐08‐01 1:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportBySubOrgDAO implements BizReportBySubOrgDAO {

    @Autowired
    private CoreReportBySubOrgRepository coreReportBySubOrgRepository;

    @EzDAOLogger
    @Override
    public List<BizReportBySubOrg> getReportAllSource(String orgId) {
        return coreReportBySubOrgRepository
                .fetchDistrictAllSource(orgId)
                .stream()
                .map(BizReportConverter::convert)
                .collect(Collectors.toList());
    }
}