/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.repo;

import id.ezclouds.core.dal.biz.dataobject.BizMigrationRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMigrationRecordRepository.java, v 0.1 2024‐10‐05 1:21 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface BizMigrationRecordRepository extends JpaRepository<BizMigrationRecordDO, String> {
}