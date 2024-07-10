/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async;

import id.ezclouds.biz.ezservice.enums.BizAsyncScene;
import id.ezclouds.biz.ezservice.service.async.processor.BizAsyncProcessor;
import id.ezclouds.biz.ezservice.service.async.processor.BizCommonReportProcessor;
import id.ezclouds.biz.ezservice.service.async.processor.BizImportMemberProcessor;
import id.ezclouds.biz.ezservice.service.request.BizAsyncProcessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizAsyncProcessService.java, v 0.1 2024‐07‐06 12:18 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizAsyncProcessService {

    @Autowired
    private BizImportMemberProcessor bizImportMemberProcessor;

    @Autowired
    private BizCommonReportProcessor bizCommonReportProcessor;

    @Async
    public void process(BizAsyncProcessRequest request) {
        for (BizAsyncProcessor bizAsyncProcessor : getProcessors(request.getBizAsyncScene())) {
            bizAsyncProcessor.process(request);
        }
    }

    private List<BizAsyncProcessor> getProcessors(BizAsyncScene bizAsyncScene) {
        List<BizAsyncProcessor> processors = new ArrayList<>();

        switch (bizAsyncScene) {
            case SYNC_MEMBER_DATA_REGISTER:
                processors.add(bizCommonReportProcessor);
                break;
            case SYNC_MEMBER_DATA_IMPORT:
                processors.add(bizImportMemberProcessor);
                processors.add(bizCommonReportProcessor);
                break;
        }

        return processors;
    }
}