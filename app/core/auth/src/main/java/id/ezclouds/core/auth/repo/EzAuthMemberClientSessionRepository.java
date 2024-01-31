/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.EzAuthMemberClientSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientSessionRepository.java, v 0.1 2024‐01‐29 2:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthMemberClientSessionRepository extends JpaRepository<EzAuthMemberClientSessionDO, String> {

    @Query("SELECT ms FROM EzAuthMemberClientSessionDO ms WHERE ms.orgId = :orgId AND ms.clientId = :clientId AND ms.status = 1")
    List<EzAuthMemberClientSessionDO> findAllByClientId(
            @Param("orgId") String orgId,
            @Param("clientId") String clientId
    );
}