/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report.repo;

import id.ezclouds.core.dal.report.dataobject.CoreReportOverallDO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportOverallRepository.java, v 0.1 2024‐07‐28 7:44 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreReportOverallRepository extends JpaRepository<CoreReportOverallDO, String> {

    CoreReportOverallDO findByOrgIdAndKeyId(String orgId, String keyId);
    List<CoreReportOverallDO> findByOrgId(String orgId);
    List<CoreReportOverallDO> findByOrgIdAndKeyIdIn(String orgId, List<String> keyIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @Query("SELECT cro FROM CoreReportOverallDO cro WHERE cro.id = ?1")
    CoreReportOverallDO findAndLockById(String reportId);

    @Modifying
    @Query(value = "UPDATE biz_report_overall SET count = :count, updated_time = :updatedTime WHERE id = :reportId", nativeQuery = true)
    void updateValue(
            @Param("reportId") String reportId,
            @Param("count") int count,
            @Param("updatedTime") String updatedTime
    );
}