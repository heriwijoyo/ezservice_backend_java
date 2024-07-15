/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.repo;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberUnionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberUnionRepository.java, v 0.1 2024‐07‐15 1:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizMemberUnionRepository extends JpaRepository<BizMemberUnionDO, String> {

    long deleteByOrgId(String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO(bmu.source, COUNT(bmu.source)) "
            + "FROM BizMemberUnionDO AS bmu WHERE bmu.orgId = ?1 GROUP BY bmu.source")
    List<BizCustomQueryGroupDO> fetchTotalBySource(String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO(bmu.role, COUNT(bmu.role)) "
            + "FROM BizMemberUnionDO AS bmu WHERE bmu.orgId = ?1 AND bmu.source = ?2 AND bmu.districtName = ?3 GROUP BY bmu.role")
    List<BizCustomQueryGroupDO> districtNameFetchRoleGroup(String orgId, String source, String districtName);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO(bmu.role, COUNT(bmu.role)) "
            + "FROM BizMemberUnionDO AS bmu WHERE bmu.orgId = ?1 AND bmu.source = ?2 AND bmu.districtName = ?3 AND bmu.villageName = ?4 GROUP BY bmu.role")
    List<BizCustomQueryGroupDO> villageNameFetchRoleGroup(String orgId, String source, String districtName, String villageName);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO(bmu.gender, COUNT(bmu.gender)) "
            + "FROM BizMemberUnionDO AS bmu WHERE bmu.orgId = ?1 AND bmu.source = ?2 AND bmu.districtName = ?3 GROUP BY bmu.gender")
    List<BizCustomQueryGroupDO> districtNameFetchGenderGroup(String orgId, String source, String districtName);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO(bmu.gender, COUNT(bmu.gender)) "
            + "FROM BizMemberUnionDO AS bmu WHERE bmu.orgId = ?1 AND bmu.source = ?2 AND bmu.districtName = ?3 AND bmu.villageName = ?4 GROUP BY bmu.gender")
    List<BizCustomQueryGroupDO> villageNameFetchGenderGroup(String orgId, String source, String districtName, String villageName);





    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizCustomQueryGroupDO(bmu.tpsNumber, COUNT(bmu.tpsNumber)) "
            + "FROM BizMemberUnionDO AS bmu WHERE bmu.orgId = ?1 AND bmu.source = ?2 AND bmu.districtName = ?3 AND bmu.villageName = ?4 GROUP BY bmu.tpsNumber")
    List<BizCustomQueryGroupDO> fetchTotalByTpsNo(String orgId, String source, String districtName, String villageName);

}