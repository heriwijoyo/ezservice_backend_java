/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal;

import id.ezclouds.common.dal.model.AppClientDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppClientRepository.java, v 0.1 2023‐12‐07 2:49 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface AppClientRepository extends JpaRepository<AppClientDO, String> {
}