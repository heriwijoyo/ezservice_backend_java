/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.organization.repo;

import id.ezclouds.core.dal.organization.dataobject.CoreSubOrganizationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSubOrganizationRepository.java, v 0.1 2024‐07‐28 9:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreSubOrganizationRepository extends JpaRepository<CoreSubOrganizationDO, String> {
    long countByOrgId(String orgId);
    List<CoreSubOrganizationDO> findByOrgId(String orgId);
}