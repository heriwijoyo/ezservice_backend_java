/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.repo;

import id.ezclouds.biz.election.service.app.dataobject.AppSurveyProcessorConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyProcessorConfigRepository.java, v 0.1 2024‐05‐10 4:33 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppSurveyProcessorConfigRepository extends JpaRepository<AppSurveyProcessorConfigDO, String> {

    AppSurveyProcessorConfigDO findByOrgIdAndSurveyIdAndQuestionVersion(String orgId, String surveyId, String questionVersion);
}