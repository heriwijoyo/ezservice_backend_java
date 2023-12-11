/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.repo.auth;

import id.ezclouds.common.dal.model.AppMemberClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberClientRepository.java, v 0.1 2023‐12‐11 2:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppMemberClientRepository extends JpaRepository<AppMemberClientDO, String> {

    @Query("SELECT c FROM AppMemberClientDO c WHERE c.orgId = :orgId AND c.appId = :appId AND c.loginType = :loginType AND c.loginId = :loginId")
    List<AppMemberClientDO> findAppMemberClient(
            @Param("orgId") String orgId,
            @Param("appId") String appId,
            @Param("loginType") String loginType,
            @Param("loginId") String loginId
    );
}