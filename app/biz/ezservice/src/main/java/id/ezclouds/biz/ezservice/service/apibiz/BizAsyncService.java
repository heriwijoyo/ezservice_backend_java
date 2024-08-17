/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.apibiz;

import id.ezclouds.biz.ezservice.service.app.AppSurveyDataService;
import id.ezclouds.biz.ezservice.service.request.BizAsyncTriggerRequest;
import id.ezclouds.common.model.result.BizResult;
import id.ezclouds.biz.ezservice.service.template.BizServiceTemplate;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncService.java, v 0.1 2024‐05‐10 11:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAsyncService extends BizBaseService {

    @Autowired
    private AppSurveyDataService appSurveyDataService;

    public BizResult triggerAsync(BizAsyncTriggerRequest request) {
        final BizResult bizResult = new BizResult();
        BizServiceTemplate.execute(request, bizResult, new BizServiceTemplate.Handler() {
            @Override
            public void onRequestCheck() throws EzErrorException {
                AssertUtil.notNull(request, EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getScene(), EzErrorCode.ILLEGAL_PARAM);
                AssertUtil.notBlank(request.getTargetId(), EzErrorCode.ILLEGAL_PARAM);
            }

            @Override
            public void onBizProcess() throws Exception {
                if ("PROCESS_SURVEY_RESPONSE".equals(request.getScene())) {
                    appSurveyDataService.processResponseAsync(request.getTargetId());
                }

                bizResult.setSuccess(true);
                bizResult.setObject("OK");
            }

            @Override
            public String getErrorMessage(EzErrorCode ezErrorCode) {
                return getBizErrorMessage(ezErrorCode);
            }
        });
        return bizResult;
    }
}