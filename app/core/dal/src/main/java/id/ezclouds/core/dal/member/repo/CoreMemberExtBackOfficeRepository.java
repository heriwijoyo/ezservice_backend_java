/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberExtBackOfficeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtBackOfficeRepository.java, v 0.1 2024‐08‐11 8:41 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberExtBackOfficeRepository extends JpaRepository<CoreMemberExtBackOfficeDO, String> {
    CoreMemberExtBackOfficeDO findByMemberId(String memberId);
    List<CoreMemberExtBackOfficeDO> findByMemberIdIn(List<String> memberIds);
}