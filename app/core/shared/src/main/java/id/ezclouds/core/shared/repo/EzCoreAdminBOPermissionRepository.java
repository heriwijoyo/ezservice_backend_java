/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreAdminBOPermissionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreAdminBOPermissionRepository.java, v 0.1 2024‐02‐11 10:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreAdminBOPermissionRepository extends JpaRepository<EzCoreAdminBOPermissionDO, String> {

    @Query("SELECT pbo FROM EzCoreAdminBOPermissionDO pbo WHERE pbo.status = 1")
    List<EzCoreAdminBOPermissionDO> findAllActive();

    List<EzCoreAdminBOPermissionDO> findByOrgId(String orgId);
}