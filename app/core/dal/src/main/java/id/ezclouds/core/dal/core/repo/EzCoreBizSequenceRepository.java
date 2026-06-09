/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.repo;

import id.ezclouds.core.dal.core.dataobject.EzCoreBizSequenceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreBizSequenceRepository.java, v 0.1 2024‐09‐28 1:56 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreBizSequenceRepository extends JpaRepository<EzCoreBizSequenceDO, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT ecbs FROM EzCoreBizSequenceDO ecbs WHERE ecbs.bizSeqId = ?1")
    EzCoreBizSequenceDO findAndLockById(String bizSeqId);
}