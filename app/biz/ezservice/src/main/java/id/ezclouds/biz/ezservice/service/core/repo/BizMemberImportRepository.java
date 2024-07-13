/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core.repo;

import id.ezclouds.biz.ezservice.service.core.dataobject.BizMemberImportDO;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMemberImportRepository.java, v 0.1 2024‐07‐07 10:34 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizMemberImportRepository extends JpaRepository<BizMemberImportDO, String> {

    List<BizMemberImportDO> findByOrgIdAndSourceId(String orgId, String sourceId);

    List<BizMemberImportDO> findByOrgIdAndSourceIdNot(String orgId, String sourceIdNot);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.subOrgId, COUNT(bmi.subOrgId)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId GROUP BY bmi.subOrgId")
    List<BizReportCustomDO> getReportGroupBySubOrg(@Param("orgId") String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.subOrgId, COUNT(bmi.phone)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId AND LENGTH(bmi.phone) >= 10 GROUP BY bmi.subOrgId")
    List<BizReportCustomDO> getPhoneGroupBySubOrg(@Param("orgId") String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.subOrgId, COUNT(bmi.idCardNumber)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId AND LENGTH(bmi.idCardNumber) >= 10 GROUP BY bmi.subOrgId")
    List<BizReportCustomDO> getIdCardGroupBySubOrg(@Param("orgId") String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.subOrgId, COUNT(bmi.gender)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId AND bmi.gender = :gender GROUP BY bmi.subOrgId")
    List<BizReportCustomDO> getGenderGroupByIdSubOrg(@Param("orgId") String orgId, @Param("gender") String gender);



    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.districtName, COUNT(bmi.districtName)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId GROUP BY bmi.districtName")
    List<BizReportCustomDO> getReportGroupByDistrict(@Param("orgId") String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.districtName, COUNT(bmi.phone)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId AND LENGTH(bmi.phone) >= 10 GROUP BY bmi.districtName")
    List<BizReportCustomDO> getPhoneGroupByDistrict(@Param("orgId") String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.districtName, COUNT(bmi.idCardNumber)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId AND LENGTH(bmi.idCardNumber) >= 10 GROUP BY bmi.districtName")
    List<BizReportCustomDO> getIdCardGroupByDistrict(@Param("orgId") String orgId);

    @Query("SELECT new id.ezclouds.biz.ezservice.service.core.dataobject.BizReportCustomDO(bmi.districtName, COUNT(bmi.gender)) "
            + "FROM BizMemberImportDO AS bmi WHERE bmi.orgId = :orgId AND bmi.gender = :gender GROUP BY bmi.districtName")
    List<BizReportCustomDO> getGenderGroupByDistrict(@Param("orgId") String orgId, @Param("gender") String gender);
}