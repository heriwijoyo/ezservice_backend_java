/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.repo;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.AppMemberFlagDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberFlagRepository.java, v 0.1 2024‐02‐04 6:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppMemberFlagRepository extends JpaRepository<AppMemberFlagDO, String> {

    @Query("SELECT mf FROM AppMemberFlagDO mf WHERE mf.orgId = :orgId AND mf.memberId = :memberId AND mf.status = 1")
    List<AppMemberFlagDO> getActiveMemberFlags(
            @Param("orgId") String orgId,
            @Param("memberId") String memberId
    );

    @Query("SELECT mf FROM AppMemberFlagDO mf WHERE mf.orgId = :orgId AND mf.memberId = :memberId AND mf.flagCode = :flagCode")
    Optional<AppMemberFlagDO> findByMemberFlagCode(
            @Param("orgId") String orgId,
            @Param("memberId") String memberId,
            @Param("flagCode") String flagCode
    );
}