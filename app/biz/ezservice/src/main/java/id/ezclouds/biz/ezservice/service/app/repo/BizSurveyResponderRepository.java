/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.repo;

import id.ezclouds.biz.ezservice.service.app.dataobject.BizSurveyResponderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyResponderRepository.java, v 0.1 2024‐02‐17 6:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizSurveyResponderRepository extends JpaRepository<BizSurveyResponderDO, Long> {

    List<BizSurveyResponderDO> findBySurveyIdInAndStatus(List<String> surveyIds, int status);
}