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

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.q001a, COUNT(cds.q001a)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.q001a" )
    List<BizGroupQueryCount> queryGroupResponse001(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.q002a, COUNT(cds.q002a)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.q002a" )
    List<BizGroupQueryCount> queryGroupResponse002(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.q003a, COUNT(cds.q003a)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.q003a" )
    List<BizGroupQueryCount> queryGroupResponse003(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.q004a, COUNT(cds.q004a)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.q004a" )
    List<BizGroupQueryCount> queryGroupResponse004(String orgId, String surveyId);

    @Query("SELECT new id.ezclouds.common.model.query.BizGroupQueryCount(cds.q005a, COUNT(cds.q005a)) " +
            "FROM EzCommonDataSurveyDO AS cds WHERE cds.orgId = ?1 AND cds.surveyId = ?2 GROUP BY cds.q005a" )
    List<BizGroupQueryCount> queryGroupResponse005(String orgId, String surveyId);
}