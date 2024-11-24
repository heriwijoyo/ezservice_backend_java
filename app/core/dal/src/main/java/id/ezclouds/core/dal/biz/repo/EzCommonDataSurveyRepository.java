/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.common.model.query.BizGroupQueryCount;
import id.ezclouds.core.dal.biz.dataobject.EzCommonDataSurveyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCommonDataSurveyRepository.java, v 0.1 2024‐08‐18 11:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCommonDataSurveyRepository extends JpaRepository<EzCommonDataSurveyDO, String> {

    @Query(value = "SELECT ecds FROM EzCommonDataSurveyDO ecds WHERE ecds.orgId = ?1 AND ecds.surveyId = ?2 ORDER BY ecds.createdTime DESC")
    List<EzCommonDataSurveyDO> findByOrgIdAndSurveyId(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.submitterId, cds.submitterName, COUNT(cds.submitterId)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.submitterId" )
    List<BizGroupQueryCount> queryGroupSubmitter(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.r001, COUNT(cds.r001)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.r001" )
    List<BizGroupQueryCount> queryGroupResponse001(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.r002, COUNT(cds.r002)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.r002" )
    List<BizGroupQueryCount> queryGroupResponse002(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.r003, COUNT(cds.r003)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.r003" )
    List<BizGroupQueryCount> queryGroupResponse003(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.r004, COUNT(cds.r004)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.r004" )
    List<BizGroupQueryCount> queryGroupResponse004(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.r005, COUNT(cds.r005)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.r005" )
    List<BizGroupQueryCount> queryGroupResponse005(String orgId, String surveyId);
}