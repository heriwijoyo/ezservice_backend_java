/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.repo;

import id.ezclouds.biz.ezservice.service.app.dataobject.AppProfileDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppProfileRepository.java, v 0.1 2023‐12‐11 12:51 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppProfileRepository extends JpaRepository<AppProfileDO, String> {
}