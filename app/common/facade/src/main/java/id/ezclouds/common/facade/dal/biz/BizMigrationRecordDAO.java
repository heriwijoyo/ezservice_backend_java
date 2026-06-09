/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal.biz;

import id.ezclouds.common.model.biz.migration.BizMigrationRecord;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMigrationRecordDAO.java, v 0.1 2024‐10‐05 1:13 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface BizMigrationRecordDAO {

    void store(BizMigrationRecord migrationRecord);

    BizMigrationRecord getById(String recordId);

    BizMigrationRecord getAndLock(String recordId);
}