/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreAppVillageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAppVillageRepository.java, v 0.1 2024‐02‐18 6:54 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreAppVillageRepository extends JpaRepository<EzCoreAppVillageDO, String> {

    List<EzCoreAppVillageDO> findByIdIn(List<String> ids);
    List<EzCoreAppVillageDO> findByDistrictIdIn(List<String> districtIds);
}