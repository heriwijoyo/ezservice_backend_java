/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.auth.repo;

import id.ezclouds.core.auth.dataobject.EzAuthMemberClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzAuthMemberClientRepository.java, v 0.1 2024‐01‐07 9:40 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzAuthMemberClientRepository extends JpaRepository<EzAuthMemberClientDO, String> {

    @Query("SELECT mc FROM EzAuthMemberClientDO mc WHERE mc.orgId = :orgId AND mc.appId = :appId AND mc.loginType = :loginType AND mc.loginId = :loginId")
    EzAuthMemberClientDO findByLoginRequest(
            @Param("orgId") String orgId,
            @Param("appId") String appId,
            @Param("loginType") String loginType,
            @Param("loginId") String loginId
    );
}