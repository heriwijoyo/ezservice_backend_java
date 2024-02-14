/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal.repo;

import id.ezclouds.common.dal.dataobject.AppConfigDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppConfigRepository.java, v 0.1 2023‐12‐09 9:30 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface AppConfigRepository extends JpaRepository<AppConfigDO, String> {

    @Query("SELECT cfg FROM AppConfigDO cfg WHERE cfg.status = 1")
    List<AppConfigDO> findAllActive();
}