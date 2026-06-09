/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.core.dal.report.dataobject.CoreReportByAreaDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportByAreaRepository.java, v 0.1 2024‐07‐31 7:08 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreReportByAreaRepository extends JpaRepository<CoreReportByAreaDO, String> {
    @Query("SELECT cra FROM CoreReportByAreaDO cra WHERE cra.orgId = ?1 AND cra.villageName = 'ALL'")
    List<CoreReportByAreaDO> fetchDistrictAllSource(String orgId);
    @Query("SELECT cra FROM CoreReportByAreaDO cra WHERE cra.orgId = ?1 AND cra.source = ?2 AND cra.villageName = 'ALL'")
    List<CoreReportByAreaDO> fetchDistrictSource(String orgId, String source);

    List<CoreReportByAreaDO> findByOrgIdAndSource(String orgId, String source);
}