/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreAppDistrictDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAppDistrictRepository.java, v 0.1 2024‐02‐18 6:51 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreAppDistrictRepository extends JpaRepository<EzCoreAppDistrictDO, String> {

    List<EzCoreAppDistrictDO> findByIdIn(List<String> ids);
    List<EzCoreAppDistrictDO> findByRegencyIdIn(List<String> regencyIds);
}