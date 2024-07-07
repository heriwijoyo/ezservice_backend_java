/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core;

import id.ezclouds.biz.ezservice.enums.BizImportScene;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCommonImportDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizCommonImportRepository;
import id.ezclouds.biz.ezservice.service.request.BizDataImportRequest;
import id.ezclouds.biz.ezservice.service.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.DateUtil;
import id.ezclouds.common.util.HashUtil;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizDataImportService.java, v 0.1 2024‐07‐07 4:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizDataImportService extends BizBaseService {

    @Autowired
    private BizCommonImportRepository bizCommonImportRepository;

    public BizResult process(BizDataImportRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notNull(request.getImportScene(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.isTrue(request.getImportScene() != BizImportScene.UNKNOWN, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getOrgId(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getFileId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                String currentTime = DateUtil.getCurrentFormattedDate();
                BizCommonImportDO bizCommonImportDO = new BizCommonImportDO();
                bizCommonImportDO.setId(
                        HashUtil.createHash(
                                request.getOrgId(),
                                request.getImportScene().getCode(),
                                currentTime)
                );
                bizCommonImportDO.setScene(request.getImportScene().getCode());
                bizCommonImportDO.setOrgId(request.getOrgId());
                bizCommonImportDO.setSubOrgId(request.getSubOrgId());
                bizCommonImportDO.setFileId(request.getFileId());
                bizCommonImportDO.setCreatedTime(currentTime);

                bizCommonImportRepository.saveAndFlush(bizCommonImportDO);

                bizResult.setSuccess(true);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}