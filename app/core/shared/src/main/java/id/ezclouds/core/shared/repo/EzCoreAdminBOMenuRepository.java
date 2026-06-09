/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreAdminBOMenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreAdminBOMenuRepository.java, v 0.1 2024‐02‐11 10:09 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreAdminBOMenuRepository extends JpaRepository<EzCoreAdminBOMenuDO, String> {

    @Query("SELECT menu FROM EzCoreAdminBOMenuDO menu WHERE menu.status = 1")
    List<EzCoreAdminBOMenuDO> findAllActive();

    List<EzCoreAdminBOMenuDO> findByOrgId(String orgId);
}