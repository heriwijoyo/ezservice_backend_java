/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.repo;

import id.ezclouds.common.dal.dataobject.ImageSlideDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SlideImageRepository.java, v 0.1 2023‐12‐10 9:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface ImageSlideRepository extends JpaRepository<ImageSlideDO, String> {

    @Query("SELECT ims FROM ImageSlideDO ims WHERE ims.section = :section AND ims.status = 1 ORDER BY ims.order")
    List<ImageSlideDO> findActiveSectionImageSlide(@Param("section") String section);
}