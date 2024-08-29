/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberBackOfficeDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberBackOfficeRepository.java, v 0.1 2024‐08‐11 12:34 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberBackOfficeRepository extends JpaRepository<CoreMemberBackOfficeDO, String> {

    Page<CoreMemberBackOfficeDO> findByOrgId(String orgId, Pageable pageable);

    Page<CoreMemberBackOfficeDO> findByOrgIdAndPhone(String orgId, String phone, Pageable pageable);

    Page<CoreMemberBackOfficeDO> findByOrgIdAndNameContains(String orgId, String name, Pageable pageable);

    List<CoreMemberBackOfficeDO> findByReferrerId(String referrerId);
}