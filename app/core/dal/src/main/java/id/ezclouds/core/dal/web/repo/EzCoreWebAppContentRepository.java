/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.web.repo;

import id.ezclouds.core.dal.web.dataobject.EzCoreWebAppContentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzCoreWebAppContentRepository.java, v 0.1 2024‐09‐19 2:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzCoreWebAppContentRepository extends JpaRepository<EzCoreWebAppContentDO, String> {
}