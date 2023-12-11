/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.repo.member;

import id.ezclouds.common.dal.model.AppMemberDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMemberRepository.java, v 0.1 2023‐12‐11 11:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppMemberRepository extends JpaRepository<AppMemberDO, String> {

    @Query("SELECT m FROM AppMemberDO m WHERE m.memberId = :memberId AND m.orgId = :orgId")
    List<AppMemberDO> findByMemberIdAndOrgId(
            @Param("memberId") String memberId,
            @Param("orgId") String orgId
    );
}