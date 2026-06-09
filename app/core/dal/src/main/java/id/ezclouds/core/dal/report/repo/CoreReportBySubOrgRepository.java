/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.core.dal.report.dataobject.CoreReportBySubOrgDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportBySubOrgRepository.java, v 0.1 2024‐07‐31 7:08 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreReportBySubOrgRepository extends JpaRepository<CoreReportBySubOrgDO, String> {
    @Query("SELECT cro FROM CoreReportBySubOrgDO cro WHERE cro.orgId = ?1")
    List<CoreReportBySubOrgDO> fetchDistrictAllSource(String orgId);
}