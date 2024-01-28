/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo.dataobject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrganizationRepository.java, v 0.1 2024‐01‐28 6:14 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreOrganizationRepository extends JpaRepository<EzCoreOrganizationDO, String> {

    @Query("SELECT org FROM EzCoreOrganizationDO org WHERE org.status = 1")
    List<EzCoreOrganizationDO> findAllActiveOrganizations();
}