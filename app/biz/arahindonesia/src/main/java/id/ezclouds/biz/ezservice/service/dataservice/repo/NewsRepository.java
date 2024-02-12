/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.repo;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.NewsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: NewsRepository.java, v 0.1 2023‐12‐10 11:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface NewsRepository extends JpaRepository<NewsDO, String> {

    @Query(value = "SELECT news FROM NewsDO news WHERE news.status = 1 AND news.highlight = 1 ORDER BY news.publishDate DESC")
    List<NewsDO> findHighlightedNews();

    @Query(value = "SELECT * FROM app_news WHERE org_id = :orgId AND status = 1 ORDER BY publish_date DESC LIMIT :limit", nativeQuery = true)
    List<NewsDO> findActiveNews(@Param("orgId") String orgId, @Param("limit") Integer limit);
}