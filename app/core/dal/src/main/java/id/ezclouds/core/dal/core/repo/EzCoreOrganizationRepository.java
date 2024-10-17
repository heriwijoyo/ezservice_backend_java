/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.repo;

import id.ezclouds.core.dal.core.dataobject.CoreOrganizationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreOrganizationRepository.java, v 0.1 2024‐09‐23 1:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreOrganizationRepository extends JpaRepository<CoreOrganizationDO, String> {
}