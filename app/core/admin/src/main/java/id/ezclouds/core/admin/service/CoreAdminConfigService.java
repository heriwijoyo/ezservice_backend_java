/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.admin.BizAdminConfigService;
import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.admin.CreateCommonTableRequest;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminConfigService.java, v 0.1 2024‐08‐17 1:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminConfigService implements BizAdminConfigService {

    @Autowired
    private BizCommonTableDAO bizCommonTableDAO;

    @Override
    public BizCommonTable createBizCommonTable(CreateCommonTableRequest request) {
        String currentTime = DateUtil.getCurrentFormattedDate();
        BizCommonTable bizCommonTable = new BizCommonTable();
        bizCommonTable.setTableId(HashUtil.createHash(request.getOrgId(), request.getCode(), currentTime));
        bizCommonTable.setOrgId(request.getOrgId());
        bizCommonTable.setCode(request.getCode());
        bizCommonTable.setTitle(request.getTitle());
        bizCommonTable.setColumns(request.getColumns());
        bizCommonTable.setConfig(request.getConfig());
        bizCommonTable.setCreatedTime(currentTime);
        bizCommonTable.setStatus(1);
        bizCommonTableDAO.store(bizCommonTable);

        return bizCommonTableDAO.getByCode(request.getOrgId(), request.getCode());
    }

    @Override
    public BizCommonTable getByCode(String orgId, String code) {
        return bizCommonTableDAO.getByCode(orgId, code);
    }
}