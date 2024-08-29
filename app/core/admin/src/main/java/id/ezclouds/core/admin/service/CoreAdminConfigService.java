/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.admin.AdminConfigService;
import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.facade.dal.config.CoreConfigDAO;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.config.CoreConfig;
import id.ezclouds.common.model.request.WebBizPageRequest;
import id.ezclouds.common.model.result.PageResult;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
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

    @Autowired
    private CoreConfigDAO coreConfigDAO;

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

    @Override
    public CoreConfig getCoreConfig(String orgId, String configKey) {
        return coreConfigDAO.getConfig(orgId, configKey);
    }

    @Override
    @Transactional
    public void updateConfigValue(String orgId, String configKey, String configValue) {
        CoreConfig coreConfig = coreConfigDAO.getConfig(orgId, configKey);
        AssertUtil.notNull(coreConfig, EzErrorCode.DATA_NOT_FOUND);

        coreConfig.setConfigValue(configValue);
        coreConfigDAO.storeConfig(coreConfig);
    }
}