/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberExtensionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberExtensionRepository.java, v 0.1 2024‐10‐05 1:33 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberExtensionRepository extends JpaRepository<CoreMemberExtensionDO, String> {

    List<CoreMemberExtensionDO> findByMemberIdIn(List<String> memberIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT cme FROM CoreMemberExtensionDO cme WHERE cme.memberId = ?1")
    CoreMemberExtensionDO findAndLockByMemberId(String memberId);
}