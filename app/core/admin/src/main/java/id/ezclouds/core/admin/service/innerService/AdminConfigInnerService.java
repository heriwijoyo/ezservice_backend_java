/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service.innerService;

import id.ezclouds.common.facade.dal.admin.BizCommonTableDAO;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AdminConfigInnerService.java, v 0.1 2024‐08‐17 6:59 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AdminConfigInnerService {

    @Autowired
    private BizCommonTableDAO bizCommonTableDAO;

    @Transactional
    public void createBizCommonTable(BizCommonTable bizCommonTable) {
        bizCommonTableDAO.store(bizCommonTable);

        BizCommonTable storedTable = bizCommonTableDAO
                .getByCode(bizCommonTable.getOrgId(), bizCommonTable.getCode());

        AssertUtil.notNull(storedTable, EzErrorCode.OPERATION_DATA_STORE_FAILED);
    }

    public BizCommonTable getByCode(String orgId, String code) {
        return bizCommonTableDAO.getByCode(orgId, code);
    }
}