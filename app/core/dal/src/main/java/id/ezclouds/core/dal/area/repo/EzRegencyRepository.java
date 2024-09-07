/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.area.repo;

import id.ezclouds.core.dal.area.dataobject.EzRegencyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzRegencyRepository.java, v 0.1 2024‐09‐07 2:00 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzRegencyRepository extends JpaRepository<EzRegencyDO, String> {
}