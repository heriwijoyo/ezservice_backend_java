/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.BizReportAccumulateTimeSeriesDO;
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
 * @version $Id: BizReportAccumulateTimeSeriesRepository.java, v 0.1 2024‐10‐25 8:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizReportAccumulateTimeSeriesRepository extends JpaRepository<BizReportAccumulateTimeSeriesDO, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT rats FROM BizReportAccumulateTimeSeriesDO rats WHERE rats.reportTimeSeriesId = ?1")
    BizReportAccumulateTimeSeriesDO findAndLockById(String accumulateId);

    List<BizReportAccumulateTimeSeriesDO> findByOrgIdAndSceneAndTimeFrameIn(String orgId, String scene, List<String> timeFrames);
}