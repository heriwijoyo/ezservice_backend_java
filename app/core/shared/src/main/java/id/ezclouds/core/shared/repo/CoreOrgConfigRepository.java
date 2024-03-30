/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreOrgConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreOrgConfigRepository.java, v 0.1 2024‐02‐04 3:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreOrgConfigRepository extends JpaRepository<EzCoreOrgConfigDO, String> {
}