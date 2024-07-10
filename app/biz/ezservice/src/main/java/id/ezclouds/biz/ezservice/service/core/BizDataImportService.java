/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.core;

import id.ezclouds.biz.ezservice.enums.BizAsyncScene;
import id.ezclouds.biz.ezservice.enums.BizImportScene;
import id.ezclouds.biz.ezservice.service.apibiz.BizBaseService;
import id.ezclouds.biz.ezservice.service.async.BizAsyncProcessService;
import id.ezclouds.biz.ezservice.service.core.dataobject.BizCommonImportDO;
import id.ezclouds.biz.ezservice.service.core.repo.BizCommonImportRepository;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
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

    @Autowired
    private BizAsyncProcessService bizAsyncProcessService;

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

                BizAsyncProcessRequest asyncProcessRequest = new BizAsyncProcessRequest();

                switch (request.getImportScene()) {
                    case MEMBER_REGISTER_CSV_2024_JULY:
                        asyncProcessRequest.setBizAsyncScene(BizAsyncScene.SYNC_MEMBER_DATA_IMPORT);
                        asyncProcessRequest.setOrgId(request.getOrgId());
                        asyncProcessRequest.getPayload().put("SUB_ORG_ID", request.getSubOrgId());
                        asyncProcessRequest.getPayload().put("FILE_ID", request.getFileId());
                        asyncProcessRequest.getPayload().put("FILE_PATH", request.getFilePath());
                        break;
                }

                bizAsyncProcessService.process(asyncProcessRequest);

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