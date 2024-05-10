/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app;

import id.ezclouds.biz.ezservice.service.app.model.AppAsyncScene;
import id.ezclouds.biz.ezservice.service.app.processor.AppSurveyResponseProcessor;
import id.ezclouds.biz.ezservice.service.app.processor.request.AppSurveyResponseProcessRequest;
import id.ezclouds.biz.ezservice.service.app.processor.result.ProcessResult;
import id.ezclouds.biz.ezservice.service.app.request.AppAsyncRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppAsyncService.java, v 0.1 2024‐05‐10 10:04 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppAsyncService {

    @Autowired
    private AppSurveyResponseProcessor appSurveyResponseProcessor;

    public ProcessResult process(AppAsyncRequest request) {
        ProcessResult result = new ProcessResult();
        if (!isValidRequest(request)) {
            result.setMessage("AppAsyncRequest is invalid");
            return result;
        }

        switch (request.getAppAsyncScene()) {
            case RJL_COMMON_SURVEY_PROCESS:
                return appSurveyResponseProcessor.process((AppSurveyResponseProcessRequest) request.getData());
        }
        return result;
    }

    private boolean isValidRequest(AppAsyncRequest request) {
        if (request == null) {
            return false;
        }

        if (request.getAppAsyncScene() == null) {
            return false;
        }

        if (request.getData() == null) {
            return false;
        }
        return true;
    }
}