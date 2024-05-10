/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.processor;

import id.ezclouds.biz.ezservice.service.app.processor.request.AppSurveyResponseProcessRequest;
import id.ezclouds.biz.ezservice.service.app.processor.result.ProcessResult;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: AppSurveyResponseProcessor.java, v 0.1 2024‐05‐10 10:26 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class AppSurveyResponseProcessor {

    public ProcessResult process(AppSurveyResponseProcessRequest request) {
        final ProcessResult result = new ProcessResult();

        System.out.println("--- Start Process SURVEY_RESPONSE ---");
        if (request == null) {

        }

        return result;
    }
}