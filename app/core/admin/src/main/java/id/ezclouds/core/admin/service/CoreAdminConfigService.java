/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.admin.AdminConfigService;
import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminConfigService.java, v 0.1 2024‐08‐17 8:08 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminConfigService implements AdminConfigService {

    @Autowired
    private BizCommonTableDAO bizCommonTableDAO;

    @Override
    public PageResult<BizCommonTable> getBizCommonTables(WebBizPageRequest request) {
        return bizCommonTableDAO.getCommonTables(request);
    }

    @Override
    @Transactional
    public void createBizCommonTable(BizCommonTable bizCommonTable) {
        bizCommonTableDAO.store(bizCommonTable);
    }

    @Override
    @Transactional
    public void updateBizCommonTable(BizCommonTable bizCommonTable) {
        bizCommonTableDAO.update(bizCommonTable);
    }

    @Override
    public BizCommonTable getBizCommonTable(String tableId) {
        return bizCommonTableDAO.getByTableId(tableId);
    }
}