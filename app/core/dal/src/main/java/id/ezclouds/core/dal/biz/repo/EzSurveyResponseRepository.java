/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.EzSurveyResponseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzSurveyResponseRepository.java, v 0.1 2024‐08‐19 6:39 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzSurveyResponseRepository extends JpaRepository<EzSurveyResponseDO, String> {

    List<EzSurveyResponseDO> findByOrgIdAndSurveyId(String orgId, String surveyId);
}