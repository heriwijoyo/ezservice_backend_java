/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.auth.repo;

import id.ezclouds.core.dal.auth.dataobject.DalAuthAppClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DalAuthAppClientRepository.java, v 0.1 2024‐08‐13 5:38 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface DalAuthAppClientRepository extends JpaRepository<DalAuthAppClientDO, String> {
    DalAuthAppClientDO findByOrgId(String orgId);
}