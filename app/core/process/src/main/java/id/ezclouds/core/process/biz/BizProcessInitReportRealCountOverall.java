/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.process.biz;

import id.ezclouds.core.process.biz.inner.BizInnerProcessRealCountInitialize;
import id.ezclouds.core.process.model.BizProcessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: BizProcessInitReportRealCountOverall.java, v 0.1 2024‐09‐17 3:06 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class BizProcessInitReportRealCountOverall extends BizAsyncProcessor {

    @Autowired
    private BizInnerProcessRealCountInitialize bizInnerProcessRealCountInitialize;

    @Override
    public BizProcessEvent getProcessEvent() {
        return BizProcessEvent.REPORT_DATA_INIT;
    }

    @Override
    protected int maxProcessTime() {
        return 30 * 1000;
    }

    @Override
    protected boolean onProcess(Object request, List<String> logData) {
        String param = (String) request;
        String orgId = param.split(",")[0];
        String scene = param.split(",")[1];
        String sceneId = param.split(",")[2];
        int sort = Integer.parseInt(param.split(",")[3]);
        logData.add("ORG_ID="+ orgId +",SCENE="+ scene +",SCENE_ID="+ sceneId);

        bizInnerProcessRealCountInitialize
                .init(orgId, scene, sceneId, null, sort);

        return true;
    }
}