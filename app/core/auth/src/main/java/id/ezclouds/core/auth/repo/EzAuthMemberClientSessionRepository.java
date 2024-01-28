/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.EzAuthMemberClientSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientSessionRepository.java, v 0.1 2024‐01‐29 2:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthMemberClientSessionRepository extends JpaRepository<EzAuthMemberClientSessionDO, String> {

    @Modifying
    @Query(value = "UPDATE ez_auth_member_client_session SET status = 0 WHERE org_id = :orgId AND client_id = :clientId", nativeQuery = true)
    void invalidateSession(
            @Param("orgId") String orgId,
            @Param("clientId") String clientId
    );
}