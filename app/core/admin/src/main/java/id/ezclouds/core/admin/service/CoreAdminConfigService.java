/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.admin.service;

import id.ezclouds.common.facade.admin.BizAdminConfigService;
import id.ezclouds.common.facade.template.BizServiceTemplate;
import id.ezclouds.common.model.biz.BizCommonTable;
import id.ezclouds.common.model.message.CommonMessageConstant;
import id.ezclouds.common.model.request.admin.CommonTableCreateRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.BizErrorMessageHelper;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.admin.service.innerService.AdminConfigInnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreAdminConfigService.java, v 0.1 2024‐08‐17 1:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class CoreAdminConfigService implements BizAdminConfigService {

    @Autowired
    private AdminConfigInnerService adminConfigInnerService;

    @Override
    @Transactional
    public BizResult createBizCommonTable(CommonTableCreateRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
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
                adminConfigInnerService.createBizCommonTable(bizCommonTable);

                bizResult.setSuccess(true);
                bizResult.setObject(CommonMessageConstant.BIZ_OPERATION_SUCCESS);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }

    @Override
    public BizResult getByCode(String orgId, String code) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notBlank(orgId, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(code, EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                BizCommonTable commonTable = adminConfigInnerService
                        .getByCode(orgId, code);
                AssertUtil.notNull(commonTable, EzErrorCode.DATA_NOT_FOUND);

                bizResult.setSuccess(true);
                bizResult.setObject(commonTable);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return BizErrorMessageHelper.getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}