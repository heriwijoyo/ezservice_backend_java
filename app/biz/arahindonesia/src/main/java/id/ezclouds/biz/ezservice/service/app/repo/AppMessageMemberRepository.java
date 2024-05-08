/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.repo;

import id.ezclouds.biz.ezservice.service.app.dataobject.AppMessageMemberDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppMessageMemberRepository.java, v 0.1 2024‐05‐09 1:11 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppMessageMemberRepository extends JpaRepository<AppMessageMemberDO, String> {

    Page<AppMessageMemberDO> findByOrgIdAndMemberId(String orgId, String memberId, Pageable pageable);
}