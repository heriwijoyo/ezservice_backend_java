/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.app.repo;

import id.ezclouds.biz.election.service.app.dataobject.AppImageGalleryDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppImageGalleryRepository.java, v 0.1 2024‐02‐12 10:27 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppImageGalleryRepository extends JpaRepository<AppImageGalleryDO, String> {

    @Query("SELECT ig FROM AppImageGalleryDO ig WHERE ig.status = 1")
    List<AppImageGalleryDO> findAllActive();

    Page<AppImageGalleryDO> findByOrgId(String orgId, Pageable pageable);

    AppImageGalleryDO findByIdAndOrgId(String id, String orgId);
}