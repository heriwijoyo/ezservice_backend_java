/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.config.repo;

import id.ezclouds.core.dal.config.dataobject.CoreOrgConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreOrgConfigRepository.java, v 0.1 2024‐08‐29 11:46 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreOrgConfigRepository extends JpaRepository<CoreOrgConfigDO, String> {

    List<CoreOrgConfigDO> findByOrgId(String orgId);

    CoreOrgConfigDO findByOrgIdAndConfigKey(String orgId, String configKey);
}