/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz;

import id.ezclouds.common.facade.dal.biz.BizMigrationRecordDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import id.ezclouds.common.model.biz.migration.BizMigrationRecord;
import id.ezclouds.core.dal.biz.converter.BizMigrationRecordConverter;
import id.ezclouds.core.dal.biz.repo.BizMigrationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizMigrationRecordDAO.java, v 0.1 2024‐10‐05 1:20 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreBizMigrationRecordDAO implements BizMigrationRecordDAO {

    @Autowired
    private BizMigrationRecordRepository bizMigrationRecordRepository;

    @Override
    @EzDAOLogger
    public void store(BizMigrationRecord migrationRecord) {
        bizMigrationRecordRepository
                .saveAndFlush(
                        new BizMigrationRecordConverter()
                                .convertStore(migrationRecord)
                );
    }

    @Override
    @EzDAOLogger
    public BizMigrationRecord getAndLock(String recordId) {
        return new BizMigrationRecordConverter()
                .convertQuery(
                        bizMigrationRecordRepository
                                .findAndLockById(recordId)
                );
    }
}