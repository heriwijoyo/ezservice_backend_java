/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateAreaDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizReportAccumulateAreaRepository.java, v 0.1 2024‐10‐03 1:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportAccumulateAreaRepository extends JpaRepository<BizReportAccumulateAreaDO, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT raa FROM BizReportAccumulateAreaDO raa WHERE raa.accumulateAreaId = ?1")
    BizReportAccumulateAreaDO findAndLockById(String accumulateAreaId);
}