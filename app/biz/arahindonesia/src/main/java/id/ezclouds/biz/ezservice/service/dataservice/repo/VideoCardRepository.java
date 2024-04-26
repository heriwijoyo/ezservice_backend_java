/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.repo;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.VideoCardDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: VideoCardRepository.java, v 0.1 2023‐12‐10 12:15 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface VideoCardRepository extends JpaRepository<VideoCardDO, String> {

    @Query("SELECT vc FROM VideoCardDO vc WHERE vc.status = 1 ORDER BY vc.sorting")
    List<VideoCardDO> findAllActive();

    Page<VideoCardDO> findByOrgId(String orgId, Pageable pageable);
}