/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.config.BizPublicUrlResolver;
import id.ezclouds.biz.ezservice.model.annotation.BizAnnotationProcessor;
import id.ezclouds.biz.ezservice.model.event.AppEventHome;
import id.ezclouds.biz.ezservice.service.app.AppEventService;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAppEventService.java, v 0.1 2024‐05‐09 2:29 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAppEventService extends BizBaseService {

    @Autowired
    private AppEventService appEventService;

    public BizResult getAppEventHome() {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(null, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {}

            @Override
            public void onBizProcess() throws Exception {
                AppEventHome appEventHome = appEventService.getAppEventHome(getOrgId());

                BizPublicUrlResolver urlResolver = getPublicOrgUrlResolver(getOrgCode());
                BizAnnotationProcessor.annotatePublicConfig(appEventHome.getHighlights(), urlResolver);

                appEventHome.getEvents().forEach(appEvent -> {
                    BizAnnotationProcessor.annotatePublicConfig(appEvent, urlResolver);
                });

                bizResult.setSuccess(true);
                bizResult.setObject(appEventHome);
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}