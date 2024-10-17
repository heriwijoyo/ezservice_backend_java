/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateProcessDO;
import org.springframework.data.domain.Pageable;
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
 * @version $Id: BizReportAccumulateProcessRepository.java, v 0.1 2024‐10‐02 2:48 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportAccumulateProcessRepository extends JpaRepository<BizReportAccumulateProcessDO, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT rp FROM BizReportAccumulateProcessDO rp WHERE rp.processId = ?1")
    BizReportAccumulateProcessDO findAndLockById(String processId);

    List<BizReportAccumulateProcessDO> findByOrgIdAndStatus(String orgId, String status, Pageable pageable);
}