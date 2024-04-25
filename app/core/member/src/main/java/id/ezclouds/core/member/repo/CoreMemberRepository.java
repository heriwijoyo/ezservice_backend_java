/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.member.repo;

import id.ezclouds.core.member.dataobject.CoreMemberDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberRepository.java, v 0.1 2023‐12‐31 9:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberRepository  extends JpaRepository<CoreMemberDO, String> {

    List<CoreMemberDO> findByOrgIdAndRolesContains(String orgId, String roles);

    Page<CoreMemberDO> findByOrgId(String orgId, Pageable pageable);

    Page<CoreMemberDO> findByOrgIdAndSubOrgId(String orgId, String subOrgId, Pageable pageable);
}