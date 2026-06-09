/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.EzAuthAppClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthAppClientRepository.java, v 0.1 2024‐01‐28 6:48 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthAppClientRepository extends JpaRepository<EzAuthAppClientDO, String> {

    @Query("SELECT apc FROM EzAuthAppClientDO apc WHERE apc.status = 1")
    List<EzAuthAppClientDO> findActiveAppClients();

    EzAuthAppClientDO findByOrgId(String orgId);
}