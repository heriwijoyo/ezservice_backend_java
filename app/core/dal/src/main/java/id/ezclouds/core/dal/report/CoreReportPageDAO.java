/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.biz.report.BizReportPageDAO;
import id.ezclouds.common.model.biz.report.BizReportPage;
import id.ezclouds.core.dal.report.converter.BizReportPageConverter;
import id.ezclouds.core.dal.report.repo.BizReportPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportPageDAO.java, v 0.1 2024‐10‐13 6:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportPageDAO implements BizReportPageDAO {

    @Autowired
    private BizReportPageRepository bizReportPageRepository;

    @Override
    public BizReportPage getReportPage(String section, String code) {
        return new BizReportPageConverter()
                .convertQuery(
                        bizReportPageRepository
                                .findBySectionAndCode(section, code)
                );
    }
}