/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.repo;

import id.ezclouds.core.member.dataobject.CoreGroupCountDO;
import id.ezclouds.core.member.dataobject.CoreMemberDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberRepository.java, v 0.1 2023‐12‐31 9:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberRepository  extends JpaRepository<CoreMemberDO, String> {

    List<CoreMemberDO> findByMemberIdIn(List<String> memberIds);

    List<CoreMemberDO> findByOrgId(String orgId);

    List<CoreMemberDO> findByOrgIdAndPhone(String orgId, String phone);

    List<CoreMemberDO> findByOrgIdAndRolesContains(String orgId, String roles);

    Page<CoreMemberDO> findByOrgId(String orgId, Pageable pageable);

    Page<CoreMemberDO> findByOrgIdAndSubOrgId(String orgId, String subOrgId, Pageable pageable);

    @Query("SELECT new id.ezclouds.core.member.dataobject.CoreGroupCountDO(cm.subOrgId, COUNT(cm.subOrgId)) "
            + "FROM CoreMemberDO AS cm WHERE cm.orgId = ?1 AND cm.createdTime >= ?2 AND cm.createdTime <= ?3 GROUP BY cm.subOrgId")
    List<CoreGroupCountDO> fetchGroupCountBySubOrg(String orgId, String startTime, String endTime);

    @Query("SELECT new id.ezclouds.core.member.dataobject.CoreGroupCountDO(cm.referrerId, COUNT(cm.referrerId)) "
            + "FROM CoreMemberDO AS cm WHERE cm.orgId = ?1 AND cm.createdTime >= ?2 AND cm.subOrgId IS NULL GROUP BY cm.referrerId")
    List<CoreGroupCountDO> fetchEmptyGroupCountByReferrerId(String orgId, String startTime);
}