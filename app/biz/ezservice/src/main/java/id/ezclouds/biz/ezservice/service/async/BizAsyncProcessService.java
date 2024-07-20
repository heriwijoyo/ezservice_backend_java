/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async;

import id.ezclouds.biz.ezservice.enums.BizAsyncScene;
import id.ezclouds.biz.ezservice.service.async.processor.*;
import id.ezclouds.biz.ezservice.service.async.processor.BizOldCommonReportProcessor;
import id.ezclouds.biz.ezservice.service.async.processor.BizOldAsyncProcessor;
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
    private BizOldSyncBatchMemberProcessor bizSyncBatchMemberProcessor;

    @Autowired
    private BizOldImportMemberProcessor bizImportMemberProcessor;

    @Autowired
    private BizOldCommonReportProcessor bizCommonReportProcessor;

    @Async
    public void process(BizAsyncProcessRequest request) {
        for (BizOldAsyncProcessor bizAsyncProcessor : getProcessors(request.getBizAsyncScene())) {
            bizAsyncProcessor.process(request);
        }
    }

    private List<BizOldAsyncProcessor> getProcessors(BizAsyncScene bizAsyncScene) {
        List<BizOldAsyncProcessor> processors = new ArrayList<>();

        switch (bizAsyncScene) {
            case SYNC_BULK_MEMBER_DATA_REGISTER:
                processors.add(bizSyncBatchMemberProcessor);
                processors.add(bizCommonReportProcessor);
                break;
            case SYNC_BULK_MEMBER_DATA_IMPORT:
                processors.add(bizImportMemberProcessor);
                processors.add(bizCommonReportProcessor);
                break;
        }

        return processors;
    }
}