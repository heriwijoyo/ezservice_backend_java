/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.EzSurveyResponseParserConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzSurveyResponseParserConfigRepository.java, v 0.1 2024‐08‐19 8:55 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzSurveyResponseParserConfigRepository extends JpaRepository<EzSurveyResponseParserConfigDO, String> {

    List<EzSurveyResponseParserConfigDO> findByOrgIdAndSurveyId(String orgId, String surveyId);
}