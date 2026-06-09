/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.repo;

import id.ezclouds.core.dal.area.dataobject.EzVillageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzVillageRepository.java, v 0.1 2024‐08‐12 5:18 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzVillageRepository extends JpaRepository<EzVillageDO, String> {
    List<EzVillageDO> findByDistrictIdIn(List<String> districtIds);
}