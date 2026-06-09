/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.core.repo;

import id.ezclouds.core.dal.core.dataobject.CoreFeatureConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreFeatureConfigRepository.java, v 0.1 2024‐10‐13 5:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface CoreFeatureConfigRepository extends JpaRepository<CoreFeatureConfigDO, String> {
}