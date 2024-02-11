/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreAdminDashboardDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreAdminDashboardRepository.java, v 0.1 2024‐02‐12 1:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreAdminDashboardRepository extends JpaRepository<EzCoreAdminDashboardDO, String> {

    @Query("SELECT dsb FROM EzCoreAdminDashboardDO dsb WHERE dsb.orgId = :orgId AND dsb.status = 1 ORDER BY dsb.sorting ASC")
    List<EzCoreAdminDashboardDO> findByOrgIdActive(@Param("orgId") String orgId);
}