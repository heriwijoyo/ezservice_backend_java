/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.repo;

import id.ezclouds.common.dal.dataobject.AppConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfigRepository.java, v 0.1 2023‐12‐09 9:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AppConfigRepository extends JpaRepository<AppConfigDO, String> {
}