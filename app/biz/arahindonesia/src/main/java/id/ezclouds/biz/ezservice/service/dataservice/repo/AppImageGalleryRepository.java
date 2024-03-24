/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.dataservice.repo;

import id.ezclouds.biz.ezservice.service.dataservice.dataobject.AppImageGalleryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
}