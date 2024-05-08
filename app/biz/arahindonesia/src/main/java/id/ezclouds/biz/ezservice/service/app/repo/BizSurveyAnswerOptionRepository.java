/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.repo;

import id.ezclouds.biz.ezservice.service.app.dataobject.BizSurveyAnswerOptionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyAnswerOptionRepository.java, v 0.1 2024‐02‐16 8:02 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizSurveyAnswerOptionRepository extends JpaRepository<BizSurveyAnswerOptionDO, String> {

    List<BizSurveyAnswerOptionDO> findBySurveyIdInAndStatus(List<String> surveyIds, int status);
}