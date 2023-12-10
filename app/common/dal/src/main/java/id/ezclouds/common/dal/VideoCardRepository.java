/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal;

import id.ezclouds.common.dal.model.VideoCardDO;
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

    @Query("SELECT vc FROM VideoCardDO vc WHERE vc.status = 1 ORDER BY vc.order")
    List<VideoCardDO> findAllActive();
}