/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.config.repo;

import id.ezclouds.core.dal.config.dataobject.CoreConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreConfigRepository.java, v 0.1 2024‐09‐08 1:47 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreConfigRepository extends JpaRepository<CoreConfigDO, String> {

    CoreConfigDO findByConfigKey(String configKey);
}