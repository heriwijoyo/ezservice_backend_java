/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.EzAuthMemberCommonSessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientSessionRepository.java, v 0.1 2024‐01‐29 2:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthMemberCommonSessionRepository extends JpaRepository<EzAuthMemberCommonSessionDO, String> {

    @Query("SELECT cs FROM EzAuthMemberCommonSessionDO cs WHERE cs.orgId = :orgId AND cs.scene = :scene AND cs.verifyStrategy = :verifyStrategy AND cs.memberId = :memberId AND cs.status = 1")
    EzAuthMemberCommonSessionDO findBySceneLoginId(
            @Param("orgId") String orgId,
            @Param("scene") String scene,
            @Param("verifyStrategy") String verifyStrategy,
            @Param("memberId") String memberId
    );
}