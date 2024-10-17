/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.biz.service.migration;

import id.ezclouds.common.facade.biz.migration.BizMigrationService;
import id.ezclouds.common.model.result.BizResult;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreBizMigrationService.java, v 0.1 2024‐10‐04 11:44 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreBizMigrationService implements BizMigrationService {

    @Override
    public BizResult migrateMemberToVoter(String orgId) {
        return null;
    }
}