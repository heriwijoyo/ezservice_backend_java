/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.core.repo;

import id.ezclouds.biz.election.service.core.dataobject.BizCommonImportDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizCommonImportRepository.java, v 0.1 2024‐07‐07 4:37 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizCommonImportRepository extends JpaRepository<BizCommonImportDO, String> {
}