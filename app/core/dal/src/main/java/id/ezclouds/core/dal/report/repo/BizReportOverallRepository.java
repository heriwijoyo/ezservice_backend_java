/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportOverallRepository.java, v 0.1 2024‐07‐28 7:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportOverallRepository extends JpaRepository<CoreReportOverallDO, String> {
    long deleteByOrgId(String orgId);
}