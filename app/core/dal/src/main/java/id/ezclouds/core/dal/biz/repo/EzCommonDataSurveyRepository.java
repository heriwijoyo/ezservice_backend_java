/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

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
}