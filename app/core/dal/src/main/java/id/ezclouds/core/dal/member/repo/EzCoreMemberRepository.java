/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreMemberRepository.java, v 0.1 2024‐10‐05 3:35 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreMemberRepository extends JpaRepository<CoreMemberDO, String> {
}