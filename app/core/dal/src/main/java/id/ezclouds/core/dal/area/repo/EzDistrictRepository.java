/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.repo;

import id.ezclouds.core.dal.area.dataobject.EzDistrictDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzDistrictRepository.java, v 0.1 2024‐08‐12 5:11 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzDistrictRepository extends JpaRepository<EzDistrictDO, String> {
    List<EzDistrictDO> findByRegencyIdIn(List<String> regencyIds);
}