/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.integration.dataservice.repo;

import id.ezclouds.core.integration.dataservice.dataobject.WatzapLogDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConnectLogsWatzapRepository.java, v 0.1 2024‐05‐16 3:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreConnectLogsWatzapRepository extends JpaRepository<WatzapLogDO, String> {

    Page<WatzapLogDO> findByOrgId(String orgId, Pageable pageable);
    Page<WatzapLogDO> findByOrgIdAndTarget(String orgId, String target, Pageable pageable);
    Page<WatzapLogDO> findByTarget(String target, Pageable pageable);
    WatzapLogDO findByIdAndOrgId(String id, String orgId);
}