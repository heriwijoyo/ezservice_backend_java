/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateClusterDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateClusterRepository.java, v 0.1 2024‐10‐21 11:41 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportAccumulateClusterRepository extends JpaRepository<BizReportAccumulateClusterDO, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT rac FROM BizReportAccumulateClusterDO rac WHERE rac.accumulateClusterId = ?1")
    BizReportAccumulateClusterDO findAndLockById(String accumulateId);
}