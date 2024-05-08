/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.repo;

import id.ezclouds.biz.ezservice.service.app.dataobject.BizSurveyDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizSurveyRepository.java, v 0.1 2024‐02‐16 8:01 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizSurveyRepository extends JpaRepository<BizSurveyDO, String> {

    @Query("SELECT s FROM BizSurveyDO s WHERE s.status = 1")
    List<BizSurveyDO> findTopSurveyActive(Pageable pageable);
}