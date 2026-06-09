/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.repo;

import id.ezclouds.core.shared.repo.dataobject.EzCoreAppRegencyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAppRegencyRepository.java, v 0.1 2024‐02‐18 6:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreAppRegencyRepository extends JpaRepository<EzCoreAppRegencyDO, String> {

    List<EzCoreAppRegencyDO> findByIdIn(List<String> ids);
    List<EzCoreAppRegencyDO> findByProvinceIdIn(List<String> provinceIds);
}