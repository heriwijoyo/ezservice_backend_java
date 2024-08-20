/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.member.repo;

import id.ezclouds.core.dal.member.dataobject.CoreMemberReportDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreMemberReportRepository.java, v 0.1 2024‐08‐01 8:17 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreMemberReportRepository extends JpaRepository<CoreMemberReportDO, String> {
    @Query("SELECT COUNT(cmr) FROM CoreMemberReportDO cmr WHERE cmr.orgId = ?1 AND cmr.createdTime >= ?2 AND cmr.createdTime <= ?3 ")
    long countByOrgIdWithinDate(String orgId, String startDate, String endDate);
}