/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.repo;

import id.ezclouds.core.dal.core.dataobject.EzCoreSequenceDO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreSequenceRepository.java, v 0.1 2023‐12‐30 3:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreSequenceRepository extends JpaRepository<EzCoreSequenceDO, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT ecs FROM EzCoreSequenceDO ecs WHERE ecs.orgId = ?1 AND ecs.scene = ?2")
    EzCoreSequenceDO findAndLockByOrgIdAndScene(String orgId, String scene);

    EzCoreSequenceDO findByOrgIdAndScene(String orgId, String scene);
}