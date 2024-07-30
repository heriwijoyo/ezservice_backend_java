/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.core.dal.report.dataobject.CoreReportTimeSeriesDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportTimeSeriesRepository.java, v 0.1 2024‐07‐31 2:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreReportTimeSeriesRepository extends JpaRepository<CoreReportTimeSeriesDO, String> {
    List<CoreReportTimeSeriesDO> findByOrgIdAndAndReportIdAndTimeFrameIn(String orgId, String reportId, List<String> timeFrames);
}