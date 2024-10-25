/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.election.repo;

import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.core.dal.biz.election.dataobject.BizVoterDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizElectionVoterRepository.java, v 0.1 2024‐09‐23 11:58 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizElectionVoterRepository extends JpaRepository<BizVoterDO, String> {

    BizVoterDO findByOrgIdAndIdCardNumber(String orgId, String idCardNumber);

    List<BizVoterDO> findByOrgIdAndDistrictIdAndVillageIdAndPollStationId(String orgId, String districtId, String villageId, String pollStationId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(bv.subOrgId, COUNT(bv.subOrgId)) "
            + "FROM BizVoterDO AS bv WHERE bv.orgId = ?1 AND bv.createdTime >= ?2 AND bv.createdTime <= ?3 AND bv.subOrgId IS NOT NULL "
            + "GROUP BY bv.subOrgId")
    List<BizGroupQueryCount> countGroupBySubOrgWithinDate(String orgId, String startDate, String endDate);
}