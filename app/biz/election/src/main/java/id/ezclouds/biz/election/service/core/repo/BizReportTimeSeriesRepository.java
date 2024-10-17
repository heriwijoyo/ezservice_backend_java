/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core.repo;

import id.ezclouds.biz.election.service.core.dataobject.BizReportTimeSeriesDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportTimeSeriesRepository.java, v 0.1 2024‐07‐17 2:19 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportTimeSeriesRepository extends JpaRepository<BizReportTimeSeriesDO, String> {

    long deleteByOrgId(String orgId);
}