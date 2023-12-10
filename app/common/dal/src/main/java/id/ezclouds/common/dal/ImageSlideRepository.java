/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal;

import id.ezclouds.common.dal.model.ImageSlideDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: SlideImageRepository.java, v 0.1 2023‐12‐10 9:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ImageSlideRepository extends JpaRepository<ImageSlideDO, String> {

    @Query("SELECT is FROM app_image_slides WHERE is.section = :section AND is.status = 1 ORDER BY is.order ASC")
    List<ImageSlideDO> findActiveSectionImageSlide(@Param("section") String section);
}