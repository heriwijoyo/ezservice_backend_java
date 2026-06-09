/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.common.model.biz.report.BizReportPage;
import id.ezclouds.core.dal.report.dataobject.BizReportPageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportPageRepository.java, v 0.1 2024‐10‐13 6:36 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportPageRepository extends JpaRepository<BizReportPageDO, BizReportPage> {

    BizReportPageDO findBySectionAndCode(String section, String code);
}