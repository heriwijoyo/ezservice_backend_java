/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.bifrost.app.api;

import id.ezclouds.biz.ezservice.service.apibiz.BizAppDocumentService;
import id.ezclouds.biz.ezservice.service.result.BizPageResult;
import id.ezclouds.core.bifrost.app.api.event.ApiEvent;
import id.ezclouds.core.bifrost.app.api.request.ApiPageRequest;
import id.ezclouds.core.bifrost.core.converter.BizRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ApiBizPageProcessor.java, v 0.1 2024‐05‐26 1:32 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class ApiBizPageProcessor {

    @Autowired
    private BizAppDocumentService bizAppDocumentService;

    public BizPageResult process(ApiEvent event, ApiPageRequest request) {
        switch (event) {
            case API_GET_APP_DOCUMENTS:
                return bizAppDocumentService.getAppDocuments(BizRequestConverter.getBizPageRequest(request));
        }
        return null;
    }
}