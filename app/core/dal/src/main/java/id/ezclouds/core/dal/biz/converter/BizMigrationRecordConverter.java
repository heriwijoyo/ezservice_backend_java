/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.biz.converter;

import id.ezclouds.common.model.biz.migration.BizMigrationRecord;
import id.ezclouds.common.model.biz.migration.MigrationScene;
import id.ezclouds.common.model.converter.CommonDOModelConverter;
import id.ezclouds.core.dal.biz.dataobject.BizMigrationRecordDO;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizMigrationRecordConverter.java, v 0.1 2024‐10‐05 1:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class BizMigrationRecordConverter extends CommonDOModelConverter<BizMigrationRecordDO, BizMigrationRecord> {

    @Override
    protected BizMigrationRecord safeConvertQuery(BizMigrationRecordDO dataObject) {
        BizMigrationRecord migrationRecord = new BizMigrationRecord();
        migrationRecord.setRecordId(dataObject.getRecordId());
        migrationRecord.setOrgId(dataObject.getOrgId());
        migrationRecord.setScene(MigrationScene.getByCode(dataObject.getScene()));
        migrationRecord.setSourceId(dataObject.getSourceId());
        migrationRecord.setTargetId(dataObject.getTargetId());
        migrationRecord.setPayload(dataObject.getPayload());
        migrationRecord.setTimestamp(dataObject.getTimestamp());
        migrationRecord.setStatus(dataObject.getStatus());
        migrationRecord.setErrorMessage(dataObject.getErrorMessage());
        return migrationRecord;
    }

    @Override
    protected BizMigrationRecordDO safeConvertStore(BizMigrationRecord model) {
        BizMigrationRecordDO migrationRecordDO = new BizMigrationRecordDO();
        migrationRecordDO.setRecordId(model.getRecordId());
        migrationRecordDO.setOrgId(model.getOrgId());
        migrationRecordDO.setScene(model.getScene().getCode());
        migrationRecordDO.setSourceId(model.getSourceId());
        migrationRecordDO.setTargetId(model.getTargetId());
        migrationRecordDO.setPayload(model.getPayload());
        migrationRecordDO.setTimestamp(model.getTimestamp());
        migrationRecordDO.setStatus(model.getStatus());
        migrationRecordDO.setErrorMessage(model.getErrorMessage());
        return migrationRecordDO;
    }
}