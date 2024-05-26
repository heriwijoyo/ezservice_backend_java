/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.config.BizPublicUrlResolverImpl;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.service.app.AppDocumentService;
import id.ezclouds.biz.ezservice.service.app.model.AppDocument;
import id.ezclouds.biz.ezservice.service.request.BizPageRequest;
import id.ezclouds.biz.ezservice.service.result.BizPageResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import id.ezclouds.core.shared.result.BizPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppDocumentService.java, v 0.1 2024‐05‐25 3:52 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppDocumentService extends BizBaseService {

    @Autowired
    private AppDocumentService appDocumentService;

    public BizPageResult getAppDocuments(BizPageRequest request) {
        final BizPageResult bizResult = new BizPageResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                adjustBizPageRequest(request);
            }

            @Override
            public void onBizProcess() throws Exception {
                BizPageInfo<AppDocument> bizPageInfo = appDocumentService.getAppDocuments(getOrgId(), request);
                BizPublicUrlResolver urlResolver = new BizPublicUrlResolverImpl(appRootPublicUrl, getOrgCode());
                bizPageInfo.getBizData().forEach(document -> {
                    BizAnnotationProcessor.annotatePublicConfig(document, urlResolver);
                });

                bizResult.setSuccess(true);
                bizResult.setBizPageInfo(bizPageInfo);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}