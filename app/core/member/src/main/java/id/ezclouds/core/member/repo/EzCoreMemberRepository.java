/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.repo;

import id.ezclouds.core.member.dataobject.CoreGroupCountDO;
import id.ezclouds.core.member.dataobject.EzCoreMemberDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreMemberRepository.java, v 0.1 2023‐12‐31 9:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreMemberRepository extends JpaRepository<EzCoreMemberDO, String> {

    List<EzCoreMemberDO> findByMemberIdIn(List<String> memberIds);

    List<EzCoreMemberDO> findByOrgId(String orgId);

    List<EzCoreMemberDO> findByOrgIdAndPhone(String orgId, String phone);

    List<EzCoreMemberDO> findByOrgIdAndRolesContains(String orgId, String roles);

    Page<EzCoreMemberDO> findByOrgId(String orgId, Pageable pageable);

    Page<EzCoreMemberDO> findByOrgIdAndSubOrgId(String orgId, String subOrgId, Pageable pageable);

    @Query("SELECT new id.ezclouds.core.member.dataobject.CoreGroupCountDO(cm.subOrgId, COUNT(cm.subOrgId)) "
            + "FROM EzCoreMemberDO AS cm WHERE cm.orgId = ?1 AND cm.createdTime >= ?2 AND cm.createdTime <= ?3 GROUP BY cm.subOrgId")
    List<CoreGroupCountDO> fetchGroupCountBySubOrg(String orgId, String startTime, String endTime);

    @Query("SELECT new id.ezclouds.core.member.dataobject.CoreGroupCountDO(cm.referrerId, COUNT(cm.referrerId)) "
            + "FROM EzCoreMemberDO AS cm WHERE cm.orgId = ?1 AND cm.createdTime >= ?2 AND cm.subOrgId IS NULL GROUP BY cm.referrerId")
    List<CoreGroupCountDO> fetchEmptyGroupCountByReferrerId(String orgId, String startTime);
}